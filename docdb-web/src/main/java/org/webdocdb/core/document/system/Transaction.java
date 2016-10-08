package org.webdocdb.core.document.system;

import java.util.Date;
import java.util.Set;

import org.webdocdb.core.document.ManagementDocument;
import org.webdocdb.core.document.annotation.Index;
import org.webdocdb.core.document.annotation.IndexField;
import org.webdocdb.core.document.annotation.PrimaryKey;

@Index(fields={@IndexField(name="transactorId")})
public class Transaction extends ManagementDocument {

	@PrimaryKey
	private String transactionId;
	private String transactorId;
	private Date beginDatetime;
	private Date limitDatetime;
	private Set<String> transactDocuments;
	
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	public String getTransactorId() {
		return transactorId;
	}
	public void setTransactorId(String transactorId) {
		this.transactorId = transactorId;
	}
	public Date getBeginDatetime() {
		return beginDatetime;
	}
	public void setBeginDatetime(Date beginDatetime) {
		this.beginDatetime = beginDatetime;
	}
	public Date getLimitDatetime() {
		return limitDatetime;
	}
	public void setLimitDatetime(Date limitDatetime) {
		this.limitDatetime = limitDatetime;
	}
	public Set<String> getTransactDocuments() {
		return transactDocuments;
	}
	public void setTransactDocuments(Set<String> transactDocuments) {
		this.transactDocuments = transactDocuments;
	}
	
	
}
