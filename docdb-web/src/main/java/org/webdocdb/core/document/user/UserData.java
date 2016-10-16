package org.webdocdb.core.document.user;

import org.webdocdb.core.document.UserDocument;
import org.webdocdb.core.document.content.DocumentContent;

import com.fasterxml.jackson.databind.JsonNode;

public class UserData extends UserDocument {

	private String documentId;
	private DocumentContent<JsonNode> documentContent;
	private String transactorId;
	private DocumentContent<JsonNode> transactContent;
	
	public String getDocumentId() {
		return documentId;
	}
	public void setDocumentId(String documentId) {
		this.documentId = documentId;
	}
	public DocumentContent<JsonNode> getDocumentContent() {
		return documentContent;
	}
	public void setDocumentContent(DocumentContent<JsonNode> documentContent) {
		this.documentContent = documentContent;
	}
	public String getTransactorId() {
		return transactorId;
	}
	public void setTransactorId(String transactorId) {
		this.transactorId = transactorId;
	}
	public DocumentContent<JsonNode> getTransactContent() {
		return transactContent;
	}
	public void setTransactContent(DocumentContent<JsonNode> transactContent) {
		this.transactContent = transactContent;
	}

	
}
