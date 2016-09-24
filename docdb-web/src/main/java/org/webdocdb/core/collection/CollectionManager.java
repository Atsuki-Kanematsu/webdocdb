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
import org.webdocdb.core.document.system.Collection;
import org.webdocdb.core.exception.CollectionAccessException;

@Service
@Scope("singleton")
public class CollectionManager {

	@Autowired
	protected MongoOperations mongo;

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

	public boolean hasCollection(String collectionName) {
		if (nameMap.containsKey(collectionName)) {
			return true;
		}
		Collection collection = findByName(collectionName);
		if (collection == null) {
			return false;
		}
		idMap.put(collection.getCollectionId(), collection);
		nameMap.put(collection.getCollectionName(), collection);
		return true;
	}
	
	public Collection create(String collectionName, int collectionType) {
		if (hasCollection(collectionName)) {
			throw new CollectionAccessException("collection already exists");
		}
		Collection collection = new Collection();
		collection.setCollectionId("todo");
		collection.setCollectionName(collectionName);
		collection.setCollectionType(collectionType);
		mongo.save(collection, "collection");
		return collection;
	}
	
	protected Collection findByName(String collectionName) {
		Criteria criteria = Criteria.where("collectionName").is(collectionName);
		Query query = new Query(criteria);
		return mongo.findOne(query, Collection.class);
	}
	
	protected Collection findById(String collectionId) {
		Criteria criteria = Criteria.where("collectionId").is(collectionId);
		Query query = new Query(criteria);
		return mongo.findOne(query, Collection.class);
	}
	
	
}
