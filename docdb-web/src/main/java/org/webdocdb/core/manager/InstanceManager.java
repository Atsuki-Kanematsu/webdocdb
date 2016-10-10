package org.webdocdb.core.manager;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.webdocdb.core.builder.IndexQueryBuilder;
import org.webdocdb.core.document.Document;
import org.webdocdb.core.document.management.AccessToken;
import org.webdocdb.core.document.management.Account;
import org.webdocdb.core.document.management.Group;
import org.webdocdb.core.document.management.Language;
import org.webdocdb.core.document.management.Message;
import org.webdocdb.core.document.system.Collection;
import org.webdocdb.core.document.system.Instance;
import org.webdocdb.core.document.system.UniqueId;
import org.webdocdb.core.transaction.TransactionThreadManager;
import org.webdocdb.core.util.StringUtil;

import com.mongodb.DBCollection;

@Service
@Scope("singleton")
public class InstanceManager {

	protected static final String DB_COLLECTION_NAME = "#sysInstance";
	
	protected static List<Class<? extends Document>> documentClasses;
	
	static {
		documentClasses = new ArrayList<>();
		documentClasses.add(Group.class);
		documentClasses.add(Account.class);
		documentClasses.add(AccessToken.class);
		documentClasses.add(Language.class);
		documentClasses.add(Message.class);
	}
	@Autowired
	protected MongoOperations mongo;

	@Autowired
	protected TransactionThreadManager transactionManager;

	@Autowired
	protected IdManager idManager;

	@Autowired
	protected CollectionManager collectionManager;

	private boolean isCreatedDBCollection = false;
	
	public void create(Instance instance) {
		createDBCollection();
		instance.setInstanceId(idManager.generateAndActivate(UniqueId.ID_TYPE_INSTANCE));
		instance.setCreateDatetime(transactionManager.getAccessDatetime());
		instance.setCreatorId(transactionManager.getAccountId());
		instance.setModifyDatetime(transactionManager.getAccessDatetime());
		instance.setModifierId(transactionManager.getAccountId());
		instance.setStatus(Document.STATUS_ENABLE);
		mongo.insert(instance, DB_COLLECTION_NAME);
		for (Class<? extends Document> clazz : documentClasses) {
			collectionManager.create(clazz.getSimpleName(), Collection.SYSTEM_COLLECTION, clazz);
		}
	}
	
	public Instance create(String instanceName) {
		Instance instance = new Instance();
		instance.setInstanceName(instanceName);
		this.create(instance);
		return instance;
	}

	public Instance getInstance(String instanceName) {
		Query query = new Query(Criteria.where("instanceName").is(instanceName));
		return mongo.findOne(query, Instance.class, DB_COLLECTION_NAME);
	}
	
	public void drop(String instanceName) {
		Instance instance = getInstance(instanceName);
		String instanceId = instance.getInstanceId();
		List<UniqueId> colIdList = idManager.findCollectionIdByInstanceId(instanceId);
		for (UniqueId colId : colIdList) {
			collectionManager.remove(colId.getUniqueId());
		}
		Query query = new Query(Criteria.where("instanceId").is(instanceId));
		for (Class<? extends Document> clazz : documentClasses) {
			String collectionName = StringUtil.toLowerCamel(clazz.getSimpleName());
			Collection collection = collectionManager.getByName(collectionName);
			mongo.remove(query, clazz, collection.getCollectionId());
		}
		idManager.removeByInstanceId(instanceId);
		mongo.remove(query, Instance.class, DB_COLLECTION_NAME);
	}
	
	public boolean exists(String instanceName) {
		Criteria criteria = Criteria.where("instanceName").is(instanceName);
		Query query = new Query(criteria);
		return mongo.exists(query, DB_COLLECTION_NAME);
	}

	protected void createDBCollection() {
		if (isCreatedDBCollection) {
			return;
		}
		if (mongo.collectionExists(DB_COLLECTION_NAME)) {
			isCreatedDBCollection = true;
			return;
		}
		DBCollection dbCol = mongo.createCollection(DB_COLLECTION_NAME);
		List<IndexQueryBuilder> queries = IndexQueryBuilder.parseDocumentClass(Instance.class);
		for (IndexQueryBuilder query : queries) {
			dbCol.createIndex(query.getKeys(), query.getOption());
		}

		isCreatedDBCollection = true;
	}
	
}
