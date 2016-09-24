package org.webdocdb.core.document.system;

import java.util.Date;

import org.webdocdb.core.document.DocumentContent;
import org.webdocdb.core.document.SystemDocument;

public class Account extends SystemDocument {

	private String accountId;
	private String accountName;
	private DocumentContent accountMeta;
	private String loginId;
	private String password;
	private String initialPassword;
	private Date passwordUpdateDatetime;
	private Date enableFrom;
	private Date enableTo;
	private String primaryGroupId;
	private String[] secondaryGroupIds;
	
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public DocumentContent getAccountMeta() {
		return accountMeta;
	}
	public void setAccountMeta(DocumentContent accountMeta) {
		this.accountMeta = accountMeta;
	}
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getInitialPassword() {
		return initialPassword;
	}
	public void setInitialPassword(String initialPassword) {
		this.initialPassword = initialPassword;
	}
	public Date getPasswordUpdateDatetime() {
		return passwordUpdateDatetime;
	}
	public void setPasswordUpdateDatetime(Date passwordUpdateDatetime) {
		this.passwordUpdateDatetime = passwordUpdateDatetime;
	}
	public Date getEnableFrom() {
		return enableFrom;
	}
	public void setEnableFrom(Date enableFrom) {
		this.enableFrom = enableFrom;
	}
	public Date getEnableTo() {
		return enableTo;
	}
	public void setEnableTo(Date enableTo) {
		this.enableTo = enableTo;
	}
	public String getPrimaryGroupId() {
		return primaryGroupId;
	}
	public void setPrimaryGroupId(String primaryGroupId) {
		this.primaryGroupId = primaryGroupId;
	}
	public String[] getSecondaryGroupIds() {
		return secondaryGroupIds;
	}
	public void setSecondaryGroupIds(String[] secondaryGroupIds) {
		this.secondaryGroupIds = secondaryGroupIds;
	}
	
	
	
}
