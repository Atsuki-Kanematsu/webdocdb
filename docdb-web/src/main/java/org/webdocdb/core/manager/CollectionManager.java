package org.webdocdb.core.manager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.webdocdb.core.builder.IndexQueryBuilder;
import org.webdocdb.core.document.Document;
import org.webdocdb.core.document.system.Collection;
import org.webdocdb.core.document.system.UniqueId;
import org.webdocdb.core.exception.CollectionAccessException;
import org.webdocdb.core.transaction.TransactionThreadManager;

import com.mongodb.DBCollection;

@Service
@Scope("singleton")
public class CollectionManager {

	
	@Autowired
	protected MongoOperations mongo;

	@Autowired
	protected TransactionThreadManager transactionManager;
	
	@Autowired
	protected IdManager idManager;
	

	private Map<String, Collection> idMap;
	private Map<String, Collection> nameMap;
	
	@PostConstruct
	public void load() {
		idMap = new HashMap<>();
		nameMap = new HashMap<>();
		List<Collection> collections = mongo.findAll(Collection.class, "collection");
		for (Collection collection : collections) {
			idMap.put(collection.getCollectionId(), collection);
			nameMap.put(collection.getCollectionName(), collection);
		}
	}
	
	public Collection getById(String collectionId) {
		if (idMap.containsKey(collectionId)) {
			return idMap.get(collectionId);
		}
		Collection collection = findById(collectionId);
		if (collection != null) {
			idMap.put(collection.getCollectionId(), collection);
			nameMap.put(collection.getCollectionName(), collection);
		}
		return collection;
	}

	public Collection getByName(String collectionName) {
		if (nameMap.containsKey(collectionName)) {
			return nameMap.get(collectionName);
		}
		Collection collection = findByName(collectionName);
		if (collection != null) {
			idMap.put(collection.getCollectionId(), collection);
			nameMap.put(collection.getCollectionName(), collection);
		}
		return collection;
	}

	public boolean exists(String collectionId) {
		if (idMap.containsKey(collectionId)) {
			return true;
		}
		Collection collection = findById(collectionId);
		if (collection == null) {
			return false;
		}
		idMap.put(collection.getCollectionId(), collection);
		return true;
	}
	
	public <D extends Document> Collection create(String collectionName, int collectionType, Class<D> documentClass) {
		if (!mongo.collectionExists("collection")) {
			defineDBColection();
		}
		if (exists(collectionName)) {
			throw new CollectionAccessException("collection already exists");
		}
		String uniqueId = idManager.generateAndActivate(UniqueId.ID_TYPE_COLLECTION);
		createDocumentCollection(uniqueId, documentClass);
		Collection collection = new Collection();
		collection.setCollectionId(uniqueId);
		collection.setInstanceId(transactionManager.getInstanceId());
		collection.setCollectionType(collectionType);
		collection.setCreatorId(transactionManager.getAccountId());
		collection.setCreateDatetime(transactionManager.getAccessDatetime());
		collection.setModifierId(transactionManager.getAccountId());
		collection.setModifyDatetime(transactionManager.getAccessDatetime());
		collection.setStatus(Document.STATUS_ENABLE);
		mongo.save(collection, "collection");
		idMap.put(uniqueId, collection);
		nameMap.put(collectionName, collection);
		return collection;
	}
	
	protected Collection findById(String collectionId) {
		if (!mongo.collectionExists("collection")) {
			return null;
		}
		Criteria criteria = Criteria.where("collectionId").is(collectionId);
		Query query = new Query(criteria);
		return mongo.findOne(query, Collection.class, "collection");
	}
	
	protected Collection findByName(String collectionName) {
		if (!mongo.collectionExists("collection")) {
			return null;
		}
		Criteria criteria = Criteria.where("collectionName").is(collectionName);
		Query query = new Query(criteria);
		return mongo.findOne(query, Collection.class, "collection");
	}
	
	public void remove(String collectionId) {
		Collection collection = findById(collectionId);
		idMap.remove(collectionId);
		mongo.remove(collection, "collection");
		mongo.dropCollection(collectionId);
	}
	
	protected void defineDBColection() {
		DBCollection dbCol = mongo.createCollection("collection");
		dbCol.createIndex("{collectionId : 1}, {unique : true}");
		dbCol.createIndex("{collectionName : 1}");
	}
	
	protected <D extends Document> void createDocumentCollection(String dbCollectionName, Class<D> documentClass) {
		DBCollection dbCol = mongo.createCollection(dbCollectionName);
		List<String> queries = IndexQueryBuilder.parseDocumentClass(documentClass);
		for (String query : queries) {
			dbCol.createIndex(query);
		}
	}
}
