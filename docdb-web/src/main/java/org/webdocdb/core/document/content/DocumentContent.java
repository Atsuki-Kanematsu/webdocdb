package org.webdocdb.core.document.content;

public abstract class DocumentContent<T> {

	private String contentType;
	private T content;
	
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	public T getContent() {
		return content;
	}
	public void setContent(T content) {
		this.content = content;
	}
	
}
