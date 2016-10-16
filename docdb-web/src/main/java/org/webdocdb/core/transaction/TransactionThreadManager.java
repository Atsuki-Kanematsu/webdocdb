package org.webdocdb.core.transaction;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.webdocdb.core.document.management.Account;
import org.webdocdb.core.service.management.TransactionService;
import org.webdocdb.core.util.StringUtil;

@Service
@Scope
public class TransactionThreadManager {

	private ThreadLocal<TransactionEntity> transactionThreadLocal = new ThreadLocal<>();

	@Autowired
	protected TransactionService transactionService;
	
	public void begin(Account account) {
		String transactionId = transactionService.begin(account);
		in(transactionId, account);
	}
	
	public void commit() {
		transactionService.commit(transactionThreadLocal.get().getTransactionId());
		out();
	}
	
	public void rollback() {
		transactionService.rollback(transactionThreadLocal.get().getTransactionId());
		out();
	}
	
	public void in(AccessAccount account) {
		
	}
	public void in(String accountId) {
		in(null, null, accountId, new Date());
	}

	public void in(Account account) {
		in(null, account.getInstanceId(), account.getAccountId(), new Date());
	}
		
	public void in(String transactionId, Account account) {
		in(transactionId, account.getInstanceId(), account.getAccountId(), new Date());
	}
		
	public void in(String transactionId, String instanceId, String accountId, Date accessDatetime) {
		TransactionEntity te = transactionThreadLocal.get();
		if (te == null) {
			te = new TransactionEntity();
		}
		if (!StringUtil.isEmpty(transactionId)) {
			te.setTransactionId(transactionId);
		}
		te.setInstanceId(instanceId);
		te.setAccountId(accountId);
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
		
	public void out() {
		transactionThreadLocal.remove();
	}
	
	protected static class TransactionEntity {
		private String instanceId;
		private String transactionId;
		private String accountId;
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
		
	}
	
	public static class AccessAccount {

		private String instanceId;
		private String accountId;
		private String groupId;
		
		public AccessAccount(String instanceId, String accountId, String groupId) {
			this.instanceId = instanceId;
			this.accountId = accountId;
			this.groupId = groupId;
		}
		
		public AccessAccount(String accountId, String groupId) {
			this(null, accountId, groupId);
		}
		

		public String getInstanceId() {
			return instanceId;
		}
		public String getAccountId() {
			return accountId;
		}
		public String getGroupId() {
			return groupId;
		}
	}
}
