package org.webdocdb.core.document.management;

import org.webdocdb.core.document.ManagementDocument;
import org.webdocdb.core.document.annotation.IdGenerate;
import org.webdocdb.core.document.annotation.Index;
import org.webdocdb.core.document.annotation.IndexField;
import org.webdocdb.core.document.annotation.Indexes;
import org.webdocdb.core.document.annotation.PrimaryKey;
import org.webdocdb.core.document.annotation.IndexField.IndexOrder;
import org.webdocdb.core.document.content.DocumentContent;
import org.webdocdb.core.document.system.UniqueId;

import com.fasterxml.jackson.databind.JsonNode;

@Indexes({
	@Index(fields={@IndexField(name="groupId", order=IndexOrder.ASC)}, unique=true),
	@Index(fields={@IndexField(name="groupName", order=IndexOrder.ASC),
			@IndexField(name="accountId", order=IndexOrder.ASC)}, unique=true),
	@Index(fields={@IndexField(name="groupType", order=IndexOrder.ASC)}),
})
public class Group extends ManagementDocument {

	public static final int GROUP_TYPE_USER = 0;
	public static final int GROUP_TYPE_SYSTEM = 1;
	
	@IdGenerate(idType = UniqueId.ID_TYPE_ACCOUNT)
	@PrimaryKey
	private String groupId;
	private String groupName;
	private DocumentContent<JsonNode> groupMeta;
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
	public DocumentContent<JsonNode> getGroupMeta() {
		return groupMeta;
	}
	public void setGroupMeta(DocumentContent<JsonNode> groupMeta) {
		this.groupMeta = groupMeta;
	}
	public int getGroupType() {
		return groupType;
	}
	public void setGroupType(int groupType) {
		this.groupType = groupType;
	}
	
	
}
