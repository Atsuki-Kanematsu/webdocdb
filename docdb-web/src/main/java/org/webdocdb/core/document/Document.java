package org.webdocdb.core.document;

import java.util.Date;

public interface Document {

	public static final int STATUS_UNSET = 0;
	public static final int STATUS_ENABLE = 1;
	public static final int STATUS_DISABLE = 2;
	public static final int STATUS_RESERVED = 3;
	public static final int STATUS_REMOVED = 4;
	
	public String getCreatorId();
	public void setCreatorId(String creatorId);
	public String getModifierId();
	public void setModifierId(String modifierId);
	public String getRemoverId();
	public void setRemoverId(String removerId);
	public Date getCreateDatetime();
	public void setCreateDatetime(Date createDatetime);
	public Date getModifyDatetime();
	public void setModifyDatetime(Date modifyDatetime);
	public Date getRemoveDatetime();
	public void setRemoveDatetime(Date removeDatetime);
	public int getStatus();
	public void setStatus(int status);

}
