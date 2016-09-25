package org.webdocdb.core.document.system;

import java.util.Date;

import org.webdocdb.core.document.Document;

public class UserCollection implements Document {

	public static final int DATA_COLLECTION = 1;
	public static final int QUEUE_COLLECTION = 2;
	public static final int FILE_COLLECTION = 3;
	
	private String collectionId;
	private String collectionName;
	private int collectionType;
	private String documentCount;
	private boolean locked = false;
	private String creatorId;
	private Date createDatetime;
	private String modifierId;
	private Date modifyDatetime;
	private String removerId;
	private Date removeDatetime;
	private int status;
	
	public String getCollectionId() {
		return collectionId;
	}
	public void setCollectionId(String collectionId) {
		this.collectionId = collectionId;
	}
	public String getCollectionName() {
		return collectionName;
	}
	public int getCollectionType() {
		return collectionType;
	}
	public void setCollectionType(int collectionType) {
		this.collectionType = collectionType;
	}
	public void setCollectionName(String collectionName) {
		this.collectionName = collectionName;
	}
	public String getDocumentCount() {
		return documentCount;
	}
	public void setDocumentCount(String documentCount) {
		this.documentCount = documentCount;
	}
	public boolean isLocked() {
		return locked;
	}
	public void setLocked(boolean locked) {
		this.locked = locked;
	}
	public String getCreatorId() {
		return creatorId;
	}
	public void setCreatorId(String creatorId) {
		this.creatorId = creatorId;
	}
	public Date getCreateDatetime() {
		return createDatetime;
	}
	public void setCreateDatetime(Date createDatetime) {
		this.createDatetime = createDatetime;
	}
	public String getModifierId() {
		return modifierId;
	}
	public void setModifierId(String modifierId) {
		this.modifierId = modifierId;
	}
	public Date getModifyDatetime() {
		return modifyDatetime;
	}
	public void setModifyDatetime(Date modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
	}
	public String getRemoverId() {
		return removerId;
	}
	public void setRemoverId(String removerId) {
		this.removerId = removerId;
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
