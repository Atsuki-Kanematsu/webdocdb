package org.webdocdb.core.document.system;

import org.webdocdb.core.document.SystemDocument;
import org.webdocdb.core.document.annotation.PrimaryKey;

public class Message extends SystemDocument {

	@PrimaryKey
	private String messageId;
	private String languageId;
	private String messageText;
	
	public String getMessageId() {
		return messageId;
	}
	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}
	public String getLanguageId() {
		return languageId;
	}
	public void setLanguageId(String languageId) {
		this.languageId = languageId;
	}
	public String getMessageText() {
		return messageText;
	}
	public void setMessageText(String messageText) {
		this.messageText = messageText;
	}

	
}
