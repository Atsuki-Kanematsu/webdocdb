package org.webdocdb.core.manager;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.webdocdb.core.builder.IndexQueryBuilder;
import org.webdocdb.core.document.Document;
import org.webdocdb.core.document.system.UniqueId;
import org.webdocdb.core.transaction.TransactionThreadManager;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;

@Service
@Scope("singleton")
public class IdManager {

	protected static final String DB_COLLECTION_NAME = "#sysUniqueId";
	
	@Autowired
	protected MongoOperations mongo;

	@Autowired
	protected TransactionThreadManager transactionManager;
	
	public synchronized String generate(int idType) {
		createDBCollection();
		return createId(idType, Document.STATUS_RESERVED);
	}
	
	public synchronized String generateAndActivate(int idType) {
		createDBCollection();
		return createId(idType, Document.STATUS_ENABLE);
	}
	
	public void activate(String id) {
		if (!exists(id)) {
			// TODO: throw new IdNotFoundException();
			throw new RuntimeException("");
		}
		UniqueId uniqueId = findById(id);
		uniqueId.setStatus(Document.STATUS_ENABLE);
		
		Criteria criteria = Criteria.where("uniqueId").is(id);
		DBObject dbObject = new BasicDBObject();
		mongo.getConverter().write(uniqueId, dbObject);
		mongo.updateFirst(new Query(criteria), Update.fromDBObject(dbObject, "_id"), DB_COLLECTION_NAME);
	}
	
	protected String createId(int idType, int status) {
		String id = UUID.randomUUID().toString();
		if (exists(id)) {
			return createId(idType, status);
		}
		UniqueId uniqueId = new UniqueId();
		uniqueId.setIdType(idType);
		if (idType == UniqueId.ID_TYPE_INSTANCE) {
			uniqueId.setInstanceId(id);
		}
		uniqueId.setUniqueId(id);
		uniqueId.setCreatorId(transactionManager.getAccountId());
		uniqueId.setCreateDatetime(transactionManager.getAccessDatetime());
		uniqueId.setModifierId(transactionManager.getAccountId());
		uniqueId.setModifyDatetime(transactionManager.getAccessDatetime());
		uniqueId.setStatus(status);
		mongo.save(uniqueId, DB_COLLECTION_NAME);
		return id;
	}
	
	protected void createDBCollection() {
		DBCollection dbCol;
		if (!mongo.collectionExists(DB_COLLECTION_NAME)) {
			dbCol = mongo.createCollection(DB_COLLECTION_NAME);
			List<IndexQueryBuilder> queries = IndexQueryBuilder.parseDocumentClass(UniqueId.class);
			for (IndexQueryBuilder query : queries) {
				dbCol.createIndex(query.getKeys(), query.getOption());
			}
		} else {
			dbCol = mongo.getCollection(DB_COLLECTION_NAME);
		}
	}
	
	protected boolean exists(String id) {
		UniqueId uniqueId = findById(id);
		return uniqueId != null;
	}
	
	protected UniqueId findById(String id) {
		DBCollection dbCol = mongo.getCollection(DB_COLLECTION_NAME);
		if (dbCol == null) {
			return null;
		}
		Criteria criteria = Criteria.where("uniqueId").is(id);
		Query query = new Query(criteria);
		UniqueId uniqueId = mongo.findOne(query, UniqueId.class, DB_COLLECTION_NAME);
		return uniqueId;
	}
	
	protected List<UniqueId> findCollectionIdByInstanceId(String instanceId) {
		Criteria criteria = Criteria.where("instanceId").is(instanceId);
		Query query = new Query(criteria);
		List<UniqueId> results = mongo.find(query, UniqueId.class, DB_COLLECTION_NAME);
		return results;
	}
	
	protected void removeByInstanceId(String instanceId) {
		Query query = new Query(Criteria.where("instanceId").is(instanceId));
		mongo.remove(query, UniqueId.class, DB_COLLECTION_NAME);
	}
}
