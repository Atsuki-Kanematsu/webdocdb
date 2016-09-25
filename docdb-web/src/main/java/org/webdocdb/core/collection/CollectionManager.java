package org.webdocdb.core.collection;

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
import org.webdocdb.core.document.Document;
import org.webdocdb.core.document.system.Collection;
import org.webdocdb.core.exception.CollectionAccessException;
import org.webdocdb.core.transaction.TransactionThreadManager;

@Service
@Scope("singleton")
public class CollectionManager {

	
	@Autowired
	protected MongoOperations mongo;

	@Autowired
	protected TransactionThreadManager transactionManager;
	

	private Map<String, Collection> idMap;
	
	@PostConstruct
	public void load() {
		idMap = new HashMap<>();
		List<Collection> collections = mongo.findAll(Collection.class, "collection");
		for (Collection collection : collections) {
			idMap.put(collection.getCollectionId(), collection);
		}
	}
	
	public Collection getById(String collectionId) {
		if (idMap.containsKey(collectionId)) {
			return idMap.get(collectionId);
		}
		Collection collection = findById(collectionId);
		if (collection != null) {
			idMap.put(collection.getCollectionId(), collection);
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
	
	public Collection create(String collectionName, int collectionType) {
		if (!mongo.collectionExists("collection")) {
			mongo.createCollection("collection");
		}
		if (exists(collectionName)) {
			throw new CollectionAccessException("collection already exists");
		}
		Collection collection = new Collection();
		collection.setCollectionId(collectionName);
		collection.setInstanceId(transactionManager.getInstanceId());
		collection.setCollectionType(collectionType);
		collection.setCreatorId(transactionManager.getAccountId());
		collection.setCreateDatetime(transactionManager.getAccessDatetime());
		collection.setModifierId(transactionManager.getAccountId());
		collection.setModifyDatetime(transactionManager.getAccessDatetime());
		collection.setStatus(Document.STATUS_ENABLE);
		mongo.save(collection, "collection");
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
	
	public void remove(String collectionId) {
		Collection collection = findById(collectionId);
		idMap.remove(collectionId);
		mongo.remove(collection, "collection");
		mongo.dropCollection(collectionId);
	}
	
	
}
