package org.webdocdb.core.document.management;

import org.webdocdb.core.document.ManagementDocument;
import org.webdocdb.core.document.annotation.Index;
import org.webdocdb.core.document.annotation.IndexField;
import org.webdocdb.core.document.annotation.Indexes;
import org.webdocdb.core.document.annotation.PrimaryKey;
import org.webdocdb.core.document.annotation.IndexField.IndexOrder;

@Indexes({
	@Index(fields={@IndexField(name="messageId", order=IndexOrder.ASC)}, unique=true),
	@Index(fields={@IndexField(name="instanceId", order=IndexOrder.ASC), 
			@IndexField(name="messageKey", order=IndexOrder.ASC)}, unique=true)
})
public class Message extends ManagementDocument {

	@PrimaryKey
	private String messageId;
	private String messageKey;
	private String languageId;
	private String messageText;
	
	public String getMessageId() {
		return messageId;
	}
	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}
	
	public String getMessageKey() {
		return messageKey;
	}
	public void setMessageKey(String messageKey) {
		this.messageKey = messageKey;
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
