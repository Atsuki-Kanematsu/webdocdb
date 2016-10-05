package org.webdocdb.core.document.system;

import org.webdocdb.core.document.DocumentContent;
import org.webdocdb.core.document.SystemDocument;
import org.webdocdb.core.document.annotation.Index;
import org.webdocdb.core.document.annotation.IndexField;
import org.webdocdb.core.document.annotation.Indexes;
import org.webdocdb.core.document.annotation.PrimaryKey;
import org.webdocdb.core.document.annotation.IndexField.IndexOrder;

@Indexes({
	@Index(fields={@IndexField(name="groupId", order=IndexOrder.ASC)}, unique=true),
	@Index(fields={@IndexField(name="groupName", order=IndexOrder.ASC)}),
	@Index(fields={@IndexField(name="groupType", order=IndexOrder.ASC)}),
})
public class Group extends SystemDocument {

	public static final int GROUP_TYPE_USER = 0;
	public static final int GROUP_TYPE_SYSTEM = 1;
	
	@PrimaryKey
	private String groupId;
	private String groupName;
	private DocumentContent groupMeta;
	private int groupType;
	
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public DocumentContent getGroupMeta() {
		return groupMeta;
	}
	public void setGroupMeta(DocumentContent groupMeta) {
		this.groupMeta = groupMeta;
	}
	public int getGroupType() {
		return groupType;
	}
	public void setGroupType(int groupType) {
		this.groupType = groupType;
	}
	
	
}
