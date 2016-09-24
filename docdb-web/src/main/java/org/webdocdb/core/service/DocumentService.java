package org.webdocdb.core.service;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.webdocdb.core.listener.DocumentListenerFactory;
import org.webdocdb.core.service.system.IdService;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

import org.webdocdb.core.collection.CollectionManager;
import org.webdocdb.core.document.Document;
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
		Class<D> documentClass = getGenericType();
		listenerFactory.callBeforeFindOne(query, documentClass);
		D document = mongo.findOne(query, getGenericType(), dbCollectionName);
		listenerFactory.callAfterFindOne(query, document, documentClass);
		return document;
	}
	
	protected List<D> find(String dbCollectionName, Query query) {
		Class<D> documentClass = getGenericType();
		listenerFactory.callBeforeFindOne(query, documentClass);
		List<D> documents = mongo.find(query, getGenericType(), dbCollectionName);
		listenerFactory.callAfterFind(query, documents, documentClass);
		return documents;
	}
	
	protected void insert(String dbCollectionName, int collectionType, D document) {
		Collection collection;
		if (!collectionManager.hasCollection(dbCollectionName)) {
			collection = collectionManager.create(dbCollectionName, collectionType);
		} else {
			collection = collectionManager.getByName(dbCollectionName);
		}
		String collectionId = collection.getCollectionId();
		listenerFactory.callBeforeInsert(document);
		mongo.insert(document, collectionId);
		listenerFactory.callAfterInsert(document);
	}
	
	protected void update(String dbCollectionName, D document) {
		Query idQuery = createIdFindQuery(document);
		D beforeDocument = findOne(dbCollectionName, idQuery);
		if (beforeDocument == null) {
			throw new RuntimeException("TODO:Entity notfound.");
		}
		
		DBObject dbObject = new BasicDBObject();
		mongo.getConverter().write(document, dbObject);
		listenerFactory.callBeforeUpdate(idQuery, beforeDocument, document);
		mongo.updateFirst(idQuery, Update.fromDBObject(dbObject, "_id"), getGenericType());
		listenerFactory.callAfterUpdate(idQuery, document);
	}
	
	protected void delete(String dbCollectionName, D document) {
		Query idQuery = createIdFindQuery(document);
		if (findOne(dbCollectionName, idQuery) == null) {
			throw new RuntimeException("TODO:Entity notfound.");
		}
		listenerFactory.callBeforeDelete(idQuery, document);
		mongo.remove(idQuery, getGenericType());
	}
	
	protected void delete(String dbCollectionName, String documentId) {
		D document = findById(dbCollectionName, documentId);
		if (document == null) {
			throw new RuntimeException("TODO:Entity notfound.");
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
}
