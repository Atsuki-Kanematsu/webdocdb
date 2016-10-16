package org.webdocdb.core.document.management;

import java.util.Date;

import org.webdocdb.core.document.ManagementDocument;
import org.webdocdb.core.document.annotation.Index;
import org.webdocdb.core.document.annotation.IndexField;
import org.webdocdb.core.document.annotation.Indexes;
import org.webdocdb.core.document.annotation.validation.Required;
import org.webdocdb.core.document.content.DocumentContent;

import com.fasterxml.jackson.databind.JsonNode;

import org.webdocdb.core.document.annotation.IndexField.IndexOrder;

@Indexes({
	@Index(fields={@IndexField(name="accessToken", order=IndexOrder.ASC)}, unique=true),
	@Index(fields={@IndexField(name="accessKey", order=IndexOrder.ASC)}),
	@Index(fields={@IndexField(name="accountId", order=IndexOrder.ASC)}),
})
public class AccessToken extends ManagementDocument {

	@Required
	private String accessToken;
	@Required
	private String accessKey;
	@Required
	private String accountId;
	@Required
	private Date firstAccessDatetime;
	@Required
	private Date lastAccessDatetime;
	private Date expireDatetime;
	private DocumentContent<JsonNode> tokenMeta;
	
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public String getAccessKey() {
		return accessKey;
	}
	public void setAccessKey(String accessKey) {
		this.accessKey = accessKey;
	}
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public Date getFirstAccessDatetime() {
		return firstAccessDatetime;
	}
	public void setFirstAccessDatetime(Date firstAccessDatetime) {
		this.firstAccessDatetime = firstAccessDatetime;
	}
	public Date getLastAccessDatetime() {
		return lastAccessDatetime;
	}
	public void setLastAccessDatetime(Date lastAccessDatetime) {
		this.lastAccessDatetime = lastAccessDatetime;
	}
	public Date getExpireDatetime() {
		return expireDatetime;
	}
	public void setExpireDatetime(Date expireDatetime) {
		this.expireDatetime = expireDatetime;
	}
	public DocumentContent<JsonNode> getTokenMeta() {
		return tokenMeta;
	}
	public void setTokenMeta(DocumentContent<JsonNode> tokenMeta) {
		this.tokenMeta = tokenMeta;
	}

	
	
}
