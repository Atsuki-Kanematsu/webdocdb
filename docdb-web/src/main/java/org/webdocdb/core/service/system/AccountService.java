package org.webdocdb.core.service.system;

import org.webdocdb.core.document.Document;
import org.webdocdb.core.document.system.Account;
import org.webdocdb.core.service.SystemDocumentService;

public class AccountService extends SystemDocumentService<Account> {

	public Account findById(String accountId) {
		return super.findById(accountId);
	}
	
	public void create(Account account) {
		super.insert(account);
	}
	
	public void modify(Account account) {
		super.update(account);
	}
	
	public void remove(String accountId) {
		super.delete(accountId);
	}
	
	public void disable(String accountId) {
		Account account = this.findById(accountId);
		account.setStatus(Document.STATUS_DISABLE);
		modify(account);
	}
	
	public void enable(String accountId) {
		Account account = this.findById(accountId);
		account.setStatus(Document.STATUS_ENABLE);
		modify(account);
	}
}
