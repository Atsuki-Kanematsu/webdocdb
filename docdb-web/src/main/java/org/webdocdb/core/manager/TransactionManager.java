package org.webdocdb.core.manager;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.webdocdb.core.document.UserDocument;

@Service
@Scope("singleton")
public class TransactionManager {

	public boolean isInsertable(String documentId) {
		return true;
	}
	
	public boolean isUpdatable(String documentId) {
		return true;
	}
	
	public UserDocument insert(UserDocument document) {
		return document;
	}
	
	public UserDocument update(UserDocument document) {
		return document;
	}
	
	public void remove(UserDocument document) {
		
	}
	
	public void lock(String documentId) {
		
	}
	
	public void unlock(String documentId) {
		
	}
	
	public void begin(String transactionId) {
		
	}
	
	public void rollback(String transactionId) {
		
	}
	
	public void commit(String transactionId) {
		
	}
}
