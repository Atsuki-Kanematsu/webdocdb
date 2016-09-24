package org.webdocdb.core.document;

public class DocumentContent {

	private String contentType;
	private Object content;
	
	public DocumentContent(String contentType, Object content) {
		this.contentType = contentType;
		this.content = content;
	}
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	public Object getContent() {
		return content;
	}
	public void setContent(Object content) {
		this.content = content;
	}

	
}
