package org.webdocdb.core.document.user;

import java.util.Date;

import org.webdocdb.core.document.DocumentContent;
import org.webdocdb.core.document.UserDocument;

public class UserQueue extends UserDocument {

	private String documentId;
	private DocumentContent documentContent;
	private String transactorId;
	private DocumentContent transactContent;
	private Date enqueueDatetime;
	private Date dequeueScheduleDatetime;
	private long failedCount;
	
	public String getDocumentId() {
		return documentId;
	}
	public void setDocumentId(String documentId) {
		this.documentId = documentId;
	}
	public DocumentContent getDocumentContent() {
		return documentContent;
	}
	public void setDocumentContent(DocumentContent documentContent) {
		this.documentContent = documentContent;
	}
	public String getTransactorId() {
		return transactorId;
	}
	public void setTransactorId(String transactorId) {
		this.transactorId = transactorId;
	}
	public DocumentContent getTransactContent() {
		return transactContent;
	}
	public void setTransactContent(DocumentContent transactContent) {
		this.transactContent = transactContent;
	}
	public Date getEnqueueDatetime() {
		return enqueueDatetime;
	}
	public void setEnqueueDatetime(Date enqueueDatetime) {
		this.enqueueDatetime = enqueueDatetime;
	}
	public Date getDequeueScheduleDatetime() {
		return dequeueScheduleDatetime;
	}
	public void setDequeueScheduleDatetime(Date dequeueScheduleDatetime) {
		this.dequeueScheduleDatetime = dequeueScheduleDatetime;
	}
	public long getFailedCount() {
		return failedCount;
	}
	public void setFailedCount(long failedCount) {
		this.failedCount = failedCount;
	}
}
