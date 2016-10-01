package org.webdocdb.core.collection;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Service;
import org.webdocdb.core.document.Document;
import org.webdocdb.core.document.system.UniqueId;
import org.webdocdb.core.transaction.TransactionThreadManager;

import com.mongodb.DBCollection;

@Service
@Scope("singleton")
public class IdManager {

	@Autowired
	protected MongoOperations mongo;

	@Autowired
	protected TransactionThreadManager transactionManager;
	
	public synchronized String generate(int idType) {
		createDBCollection();
		return reserveId(idType);
	}
	
	public void active(String id) {
		if (!exists(id)) {
			// TODO: throw new IdNotFoundException();
			throw new RuntimeException("");
		}
		
	}
	
	protected String reserveId(int idType) {
		String id = UUID.randomUUID().toString();
		if (exists(id)) {
			return reserveId(idType);
		}
		UniqueId uniqueId = new UniqueId();
		uniqueId.setIdType(idType);
		uniqueId.setInstanceId(transactionManager.getInstanceId());
		uniqueId.setUniqueId(id);
		uniqueId.setCreatorId(transactionManager.getAccountId());
		uniqueId.setCreateDatetime(transactionManager.getAccessDatetime());
		uniqueId.setModifierId(transactionManager.getAccountId());
		uniqueId.setModifyDatetime(transactionManager.getAccessDatetime());
		uniqueId.setStatus(Document.STATUS_RESERVED);
		return id;
	}
	
	protected void createDBCollection() {
		DBCollection dbCol = mongo.getCollection("uniqueId");
		if (dbCol != null) {
			return;
		}
		dbCol = mongo.createCollection("uniqueId");
		dbCol.createIndex("{uniqueId : 1}, {unique : true}");
		dbCol.createIndex("{idType : 1}");
	}
	protected boolean exists(String id) {
		return false;
	}
	
}
