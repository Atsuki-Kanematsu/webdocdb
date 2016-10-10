package org.webdocdb.core.document.management;

import java.util.Date;

import org.webdocdb.core.document.DocumentContent;
import org.webdocdb.core.document.ManagementDocument;
import org.webdocdb.core.document.annotation.IdGenerate;
import org.webdocdb.core.document.annotation.Index;
import org.webdocdb.core.document.annotation.IndexField;
import org.webdocdb.core.document.annotation.IndexField.IndexOrder;
import org.webdocdb.core.document.system.UniqueId;
import org.webdocdb.core.document.annotation.Indexes;
import org.webdocdb.core.document.annotation.PrimaryKey;

@Indexes({
	@Index(fields={@IndexField(name="accountId", order=IndexOrder.ASC)}, unique=true),
	@Index(fields={@IndexField(name="accountId", order=IndexOrder.ASC), 
			@IndexField(name="loginIdId", order=IndexOrder.ASC)}, unique=true),
	@Index(fields={@IndexField(name="primaryGroupId", order=IndexOrder.ASC)}),
	@Index(fields={@IndexField(name="secondaryGroupIds", order=IndexOrder.ASC)}),
})
public class Account extends ManagementDocument {

	@IdGenerate(idType = UniqueId.ID_TYPE_ACCOUNT)
	@PrimaryKey
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
