package org.webdocdb.core.document.user;

import java.io.File;

import org.webdocdb.core.document.UserDocument;
import org.webdocdb.core.document.content.DocumentContent;

public class UserFile extends UserDocument {

	private String documentId;
	private DocumentContent<File> documentContent;
	private String transactorId;
	private DocumentContent<File> transactContent;
	private String saveDirectory;
	public String getDocumentId() {
		return documentId;
	}
	public void setDocumentId(String documentId) {
		this.documentId = documentId;
	}
	public DocumentContent<File> getDocumentContent() {
		return documentContent;
	}
	public void setDocumentContent(DocumentContent<File> documentContent) {
		this.documentContent = documentContent;
	}
	public String getTransactorId() {
		return transactorId;
	}
	public void setTransactorId(String transactorId) {
		this.transactorId = transactorId;
	}
	public DocumentContent<File> getTransactContent() {
		return transactContent;
	}
	public void setTransactContent(DocumentContent<File> transactContent) {
		this.transactContent = transactContent;
	}
	public String getSaveDirectory() {
		return saveDirectory;
	}
	public void setSaveDirectory(String saveDirectory) {
		this.saveDirectory = saveDirectory;
	}
}
