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
	
	public void begin(Account account, Date accessDatetime) {
		String transactionId = transactionService.begin(account);
		in(transactionId, account, accessDatetime);
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
		in(null, account, accessDatetime);
	}
	
	public void in(String transactionId, Account account, Date accessDatetime) {
		TransactionEntity te = transactionThreadLocal.get();
		if (te == null) {
			te = new TransactionEntity();
		}
		if (!StringUtil.isEmpty(transactionId)) {
			te.setTransactionId(transactionId);
		}
		if (account != null) {
			te.setInstanceId(account.getInstanceId());
			te.setAccount(account);
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
	
	public Account getAccessAccount() {
		return transactionThreadLocal.get().getAccount();
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
		private Account account;
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
		public Account getAccount() {
			return account;
		}
		public void setAccount(Account account) {
			this.account = account;
		}
		public Date getAccessDatetime() {
			return accessDatetime;
		}
		public void setAccessDatetime(Date accessDatetime) {
			this.accessDatetime = accessDatetime;
		}
		
	}
}
