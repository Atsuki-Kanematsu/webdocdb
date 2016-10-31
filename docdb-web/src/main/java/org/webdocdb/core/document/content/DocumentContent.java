package org.webdocdb.core.document.content;

import com.mongodb.DBObject;

public abstract class DocumentContent {

	private String contentType;
	private DBObject content;
	
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	public DBObject getContent() {
		return content;
	}
	public void setContent(DBObject content) {
		this.content = content;
	}
	
}
