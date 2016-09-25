package org.webdocdb.core.service;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.webdocdb.core.listener.DocumentListenerFactory;
import org.webdocdb.core.service.system.IdService;
import org.webdocdb.core.transaction.TransactionThreadManager;
import org.webdocdb.core.util.StringUtil;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

import org.webdocdb.core.collection.CollectionManager;
import org.webdocdb.core.document.Document;
import org.webdocdb.core.document.SystemDocument;
import org.webdocdb.core.document.UserDocument;
import org.webdocdb.core.document.annotation.Id;
import org.webdocdb.core.document.system.Collection;

@SuppressWarnings("unchecked")
public abstract class DocumentService<D extends Document> {

	@Autowired
	protected MongoOperations mongo;
	
	@Autowired
	protected CollectionManager collectionManager;
	
	@Autowired
	protected IdService idService;
	
	@Autowired
	protected DocumentListenerFactory listenerFactory;
	
	@Autowired
	protected TransactionThreadManager transactionManager;
	
	
	protected boolean stopListening = false;
	
	protected Class<D> getGenericType() {
		Type gs = this.getClass().getGenericSuperclass();
		if (gs instanceof ParameterizedType) {
			ParameterizedType pt = (ParameterizedType) gs;
			Type type = pt.getActualTypeArguments()[0];
			return (Class<D>) type;
		}
		return null;
	}
	
	protected D findById(String dbCollectionName, String id) {
		String idField = getIdField().getName();
		Criteria criteria = Criteria.where(idField).is(id);
		return findOne(dbCollectionName, new Query(criteria));
	}
	
	protected D findOne(String dbCollectionName, Query query) {
		if (!collectionManager.exists(dbCollectionName)) {
			return null;
		}
		Class<D> documentClass = getGenericType();
		if (!stopListening) {
			listenerFactory.callBeforeFindOne(query, documentClass);
		}
		D document = mongo.findOne(query, getGenericType(), dbCollectionName);
		if (!stopListening) {
			listenerFactory.callAfterFindOne(query, document, documentClass);
		}
		return document;
	}
	
	protected List<D> find(String dbCollectionName, Query query) {
		if (!collectionManager.exists(dbCollectionName)) {
			return new ArrayList<>();
		}
		Class<D> documentClass = getGenericType();
		if (!stopListening) {
			listenerFactory.callBeforeFindOne(query, documentClass);
		}
		List<D> documents = mongo.find(query, getGenericType(), dbCollectionName);
		if (!stopListening) {
			listenerFactory.callAfterFind(query, documents, documentClass);
		}
		return documents;
	}
	
	protected void insert(String dbCollectionName, int collectionType, D document) {
		Collection collection;
		if (!collectionManager.exists(dbCollectionName)) {
			collection = collectionManager.create(dbCollectionName, collectionType);
		} else {
			collection = collectionManager.getById(dbCollectionName);
		}
		String collectionId = collection.getCollectionId();
		setTimestamp(document, false);
		listenerFactory.callBeforeInsert(document);
		mongo.insert(document, collectionId);
		listenerFactory.callAfterInsert(document);
	}
	
	protected void update(String dbCollectionName, D document) {
		Query idQuery = createIdFindQuery(document);
		stopListening = true;
		D beforeDocument = null;
		try {
			beforeDocument = findOne(dbCollectionName, idQuery);
			if (beforeDocument == null) {
				throw new RuntimeException("TODO:Entity notfound.");
			}
		} finally {
			stopListening = false;
		}
		setTimestamp(document, false);
		DBObject dbObject = new BasicDBObject();
		mongo.getConverter().write(document, dbObject);
		listenerFactory.callBeforeUpdate(idQuery, beforeDocument, document);
		mongo.updateFirst(idQuery, Update.fromDBObject(dbObject, "_id"), getGenericType());
		listenerFactory.callAfterUpdate(idQuery, document);
	}
	
	protected void delete(String dbCollectionName, D document) {
		Query idQuery = createIdFindQuery(document);
		stopListening = true;
		try {
			if (findOne(dbCollectionName, idQuery) == null) {
				throw new RuntimeException("TODO:Entity notfound.");
			}
		} finally {
			stopListening = false;
		}
		setTimestamp(document, true);
		listenerFactory.callBeforeDelete(idQuery, document);
		mongo.remove(idQuery, getGenericType());
		listenerFactory.callAfterDelete(idQuery, document);
	}
	
	protected void delete(String dbCollectionName, String documentId) {
		stopListening = true;
		D document = null;
		try {
			document = findById(dbCollectionName, documentId);
			if (document == null) {
				throw new RuntimeException("TODO:Entity notfound.");
			}
		} finally {
			stopListening = false;
		}
		delete(dbCollectionName, document);
	}
	
	protected Query createIdFindQuery(D document) {
		Field idField = getIdField();
		idField.setAccessible(true);
		Object value;
		try {
			value = idField.get(document);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			throw new RuntimeException("TODO:idfield not found");
		}
		Criteria criteria = Criteria.where(idField.getName()).is(value);
		return new Query(criteria);
	}
	
	protected Field getIdField() {
		Class<D> clazz = getGenericType();
		for (Field field : clazz.getDeclaredFields()) {
			Id id = field.getDeclaredAnnotation(Id.class);
			if (id == null) {
				continue;
			}
			return field;
		}
		return null;
	}
		
	protected void setTimestamp(D document, boolean isRemoved) {
		String accountId = transactionManager.getAccountId();
		Date date = transactionManager.getAccessDatetime();
		String instanceId = transactionManager.getInstanceId();
		if (StringUtil.isEmpty(accountId)) {
			accountId = "unknown";
		}
		if (!StringUtil.isEmpty(instanceId)) {
			instanceId = "unknown";
		}
		if (date == null) {
			date = new Date();
		}
		if (document instanceof SystemDocument) {
			((SystemDocument) document).setInstanceId(instanceId);
		}
		if (document instanceof UserDocument) {
			((UserDocument) document).setInstanceId(instanceId);
		}
		
		if (StringUtil.isEmpty(document.getCreatorId())) {
			document.setCreatorId(accountId);
			document.setCreateDatetime(date);
		}
		document.setModifierId(accountId);
		document.setModifyDatetime(date);
		if (document.getStatus() == Document.STATUS_UNSET) {
			document.setStatus(Document.STATUS_ENABLE);
		}
		
		if (isRemoved) {
			document.setRemoverId(accountId);
			document.setRemoveDatetime(date);
			document.setStatus(Document.STATUS_REMOVED);
		}
	}
}
