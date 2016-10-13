package org.webdocdb.core.document;

import java.util.Date;

import org.springframework.data.annotation.Transient;
import org.webdocdb.core.util.StringUtil;

public abstract class AbstractDocument implements Document {

	private String nodePath;
	private String creatorId;
	private String modifierId;
	private String removerId;
	private Date createDatetime;
	private Date modifyDatetime;
	private Date removeDatetime;
	private int status;
	
	@Transient
	public String[] getNode() {
		if (StringUtil.isEmpty(nodePath)) {
			return new String[]{};
		}
		if (nodePath.startsWith("/")) {
			return nodePath.substring(1).split("/");
		}
		return nodePath.split("/");
	}
	
	public String getNodePath() {
		return nodePath;
	}
	public void setNodePath(String nodePath) {
		this.nodePath = nodePath;
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
