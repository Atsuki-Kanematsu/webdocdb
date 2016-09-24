package org.webdocdb.core.document;

import java.util.Date;

public abstract class UserDocument extends AbstractDocument implements Document {

	private String collectionId;
	private String instanceId;
	
	private String creatorId;
	private String modifierId;
	private String removerId;
	private Date createDatetime;
	private Date modifyDatetime;
	private Date removeDatetime;
	private int status;

	
	public String getCollectionId() {
		return collectionId;
	}
	public void setCollectionId(String collectionId) {
		this.collectionId = collectionId;
	}
	public String getInstanceId() {
		return instanceId;
	}
	public void setInstanceId(String instanceId) {
		this.instanceId = instanceId;
	}
	public String getCreatorId() {
		return creatorId;
	}
	public void setCreatorId(String creatorId) {
		this.creatorId = creatorId;
	}
	public String getModifierId() {
		return modifierId;
	}
	public void setModifierId(String modifierId) {
		this.modifierId = modifierId;
	}
	public String getRemoverId() {
		return removerId;
	}
	public void setRemoverId(String removerId) {
		this.removerId = removerId;
	}
	public Date getCreateDatetime() {
		return createDatetime;
	}
	public void setCreateDatetime(Date createDatetime) {
		this.createDatetime = createDatetime;
	}
	public Date getModifyDatetime() {
		return modifyDatetime;
	}
	public void setModifyDatetime(Date modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
	}
	public Date getRemoveDatetime() {
		return removeDatetime;
	}
	public void setRemoveDatetime(Date removeDatetime) {
		this.removeDatetime = removeDatetime;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}

	
	
}
