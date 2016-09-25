package org.webdocdb.core.transaction;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.webdocdb.core.document.system.Account;
import org.webdocdb.core.service.system.TransactionService;
import org.webdocdb.core.util.StringUtil;

@Service
@Scope
public class TransactionThreadManager {

	private ThreadLocal<TransactionEntity> transactionThreadLocal = new ThreadLocal<>();

	@Autowired
	protected TransactionService transactionService;
	
	public void begin(Account account, Date accessDatetime, String collectionName) {
		String transactionId = transactionService.begin(account);
		in(transactionId, account, accessDatetime, collectionName);
	}
	
	public void commit() {
		transactionService.commit(transactionThreadLocal.get().getTransactionId());
		out();
	}
	
	public void rollback() {
		transactionService.rollback(transactionThreadLocal.get().getTransactionId());
		out();
	}
	
	public void in(Account account, Date accessDatetime) {
		in(null, account, accessDatetime, null);
	}
	
	public void in(Account account, Date accessDatetime, String collectionId) {
		in(null, account, accessDatetime, collectionId);
	}
	
	public void in(String transactionId, Account account, Date accessDatetime, String collectionName) {
		TransactionEntity te = transactionThreadLocal.get();
		if (te == null) {
			te = new TransactionEntity();
		}
		if (!StringUtil.isEmpty(transactionId)) {
			te.setTransactionId(transactionId);
		}
		if (account != null) {
			te.setInstanceId(account.getInstanceId());
			te.setAccountId(account.getAccountId());
		}
		te.setAccessDatetime(accessDatetime);
		transactionThreadLocal.set(te);
	}
	
	public String getInstanceId() {
		return transactionThreadLocal.get().getInstanceId();
	}
	public String getTransactionId() {
		return transactionThreadLocal.get().getTransactionId();
	}

	public String getAccountId() {
		return transactionThreadLocal.get().getAccountId();
	}
	
	public Date getAccessDatetime() {
		return transactionThreadLocal.get().getAccessDatetime();
	}
	
	public String getCollectionName() {
		return transactionThreadLocal.get().getCollectionName();
	}
	
	public void out() {
		transactionThreadLocal.remove();
	}
	
	protected static class TransactionEntity {
		private String instanceId;
		private String transactionId;
		private String accountId;
		private String collectionName;
		private Date accessDatetime;
		
		
		public String getInstanceId() {
			return instanceId;
		}
		public void setInstanceId(String instanceId) {
			this.instanceId = instanceId;
		}
		public String getTransactionId() {
			return transactionId;
		}
		public void setTransactionId(String transactionId) {
			this.transactionId = transactionId;
		}
		public String getAccountId() {
			return accountId;
		}
		public void setAccountId(String accountId) {
			this.accountId = accountId;
		}
		public Date getAccessDatetime() {
			return accessDatetime;
		}
		public void setAccessDatetime(Date accessDatetime) {
			this.accessDatetime = accessDatetime;
		}
		public String getCollectionName() {
			return collectionName;
		}
		public void setCollectionName(String collectionName) {
			this.collectionName = collectionName;
		}
		
	}
}
