package org.webdocdb.core.document.system;

import java.util.Date;

import org.webdocdb.core.document.DocumentContent;
import org.webdocdb.core.document.SystemDocument;

public class AccessToken extends SystemDocument {

	private String accessToken;
	private String accessKey;
	private String accountId;
	private Date firstAccessDatetime;
	private Date lastAccessDatetime;
	private Date expireDatetime;
	private DocumentContent tokenMeta;
	
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
	public DocumentContent getTokenMeta() {
		return tokenMeta;
	}
	public void setTokenMeta(DocumentContent tokenMeta) {
		this.tokenMeta = tokenMeta;
	}

	
	
}
