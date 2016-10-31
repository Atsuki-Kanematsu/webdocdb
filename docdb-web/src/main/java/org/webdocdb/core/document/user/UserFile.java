package org.webdocdb.core.document.user;

import org.webdocdb.core.document.UserDocument;
import org.webdocdb.core.document.content.DocumentContent;

public class UserFile extends UserDocument {

	private String documentId;
	private DocumentContent documentContent;
	private String transactorId;
	private DocumentContent transactContent;
	private String saveDirectory;
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
	public String getSaveDirectory() {
		return saveDirectory;
	}
	public void setSaveDirectory(String saveDirectory) {
		this.saveDirectory = saveDirectory;
	}
}
