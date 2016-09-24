package org.webdocdb.core.service.system;

import java.util.Date;
import java.util.HashSet;

import org.springframework.stereotype.Service;
import org.webdocdb.core.document.system.Account;
import org.webdocdb.core.document.system.Collection;
import org.webdocdb.core.document.system.Transaction;
import org.webdocdb.core.service.SystemDocumentService;

@Service
public class TransactionService extends SystemDocumentService<Transaction> {

	protected static final int TRANSACTION_LIMIT_TIME_MILLES = 10000;
	
	public String begin(Account account) {
		Transaction tran = new Transaction();
		tran.setTransactorId(account.getAccountId());
		tran.setBeginDatetime(new Date());
		tran.setLimitDatetime(new Date());
		tran.setTransactDocuments(new HashSet<>());
		super.insert(tran, Collection.SYSTEM_COLLECTION);
		return tran.getTransactionId();
	}
	
	public void rollback(String transactionId) {
		
	}
	
	public void commit(String transactionId) {
		
	}
	
	
}
