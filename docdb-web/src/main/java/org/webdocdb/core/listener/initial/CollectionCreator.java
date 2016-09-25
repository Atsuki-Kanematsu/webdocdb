package org.webdocdb.core.listener.initial;

import java.util.List;
import java.util.UUID;

import org.springframework.context.ApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;
import org.webdocdb.core.document.Document;
import org.webdocdb.core.document.SystemDocument;
import org.webdocdb.core.document.system.SystemCollection;
import org.webdocdb.core.document.system.UserCollection;
import org.webdocdb.core.listener.DocumentRegisterListener;
import org.webdocdb.core.listener.DocumentSelectListener;
import org.webdocdb.core.service.system.IdService;

import com.mongodb.DBCollection;

public class CollectionCreator implements DocumentSelectListener, DocumentRegisterListener {

	private MongoOperations mongo;
	private IdService idService;
	
	public CollectionCreator(ApplicationContext context) {
		this.mongo = context.getBean(MongoOperations.class);
	}
	
	@Override
	public void beforeInsert(Object document) {
		if (document instanceof SystemDocument) {
			createSystemCollection(document.getClass().getSimpleName());
		}
	}

	@Override
	public void afterInsert(Object document) {
		
	}

	@Override
	public void beforeUpdate(Query query, Object before, Object after) {
		
	}

	@Override
	public void afterUpdate(Query query, Object after) {
		
	}

	@Override
	public void beforeDelete(Query query, Document document) {
		
	}

	@Override
	public void afterDelete(Query query, Document document) {
		
	}

	@Override
	public <D extends Document> void beforeFind(Query query, Class<D> documentClass) {
		Class<?> clazz = documentClass;
		do {
			if (clazz == SystemDocument.class) {
				createSystemCollection(documentClass.getSimpleName());
				break;
			}
			clazz = clazz.getSuperclass();
		} while (clazz != null);
	}

	@Override
	public <D extends Document> void afterFind(Query query, List<D> foundDocuments, Class<D> documentClass) {
		
	}

	@Override
	public <D extends Document> void beforeFindOne(Query query, Class<D> documentClass) {
		Class<?> clazz = documentClass;
		do {
			if (clazz == SystemDocument.class) {
				createSystemCollection(documentClass.getSimpleName());
				break;
			}
			clazz = clazz.getSuperclass();
		} while (clazz != null);
	}

	@Override
	public <D extends Document> void afterFindOne(Query idQuery, D foundDocument, Class<D> documentClass) {
		
	}
	
	protected void createSystemCollection(String dbCollectionName) {
		if (!mongo.collectionExists("systemCollection")) {
			DBCollection dbCol = mongo.createCollection("systemCollection");
			dbCol.createIndex("{collectionId : 1} {unique : true}");
		}
		SystemCollection collection = new SystemCollection();
		collection.setCollectionId(dbCollectionName);
		collection.setLocked(false);
		mongo.insert(collection, "systemCollection");
	}

	protected void createUserCollection(String collectionName, int collectionType) {
		if (!mongo.collectionExists("userCollection")) {
			DBCollection dbCol = mongo.createCollection("userCollection");
			dbCol.createIndex("{collectionId : 1} {unique : true}");
			dbCol.createIndex("{collectionName : 1}");
		}
		String collectionId = UUID.randomUUID().toString();
		UserCollection collection = new UserCollection();
		collection.setCollectionId(collectionId);
		collection.setCollectionName(collectionName);
		collection.setCollectionType(collectionType);
		collection.setLocked(false);
		mongo.insert(collection, "userCollection");
	}
}
