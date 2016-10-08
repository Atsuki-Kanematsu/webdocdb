package org.webdocdb.core.document.system;

import org.springframework.data.mongodb.core.mapping.Field;
import org.webdocdb.core.document.AbstractDocument;
import org.webdocdb.core.document.DocumentContent;
import org.webdocdb.core.document.annotation.Index;
import org.webdocdb.core.document.annotation.IndexField;
import org.webdocdb.core.document.annotation.Indexes;
import org.webdocdb.core.document.annotation.PrimaryKey;
import org.webdocdb.core.document.annotation.IndexField.IndexOrder;

@Indexes({
	@Index(fields={@IndexField(name="instanceId", order=IndexOrder.ASC)}, unique=true),
})
public class Instance extends AbstractDocument {

	@PrimaryKey
	@Field("instanceId")
	private String instanceId;
	private String instanceName;
	private DocumentContent instanceMeta;
	
	public String getInstanceId() {
		return instanceId;
	}
	public void setInstanceId(String instanceId) {
		this.instanceId = instanceId;
	}
	public String getInstanceName() {
		return instanceName;
	}
	public void setInstanceName(String instanceName) {
		this.instanceName = instanceName;
	}
	public DocumentContent getInstanceMeta() {
		return instanceMeta;
	}
	public void setInstanceMeta(DocumentContent instanceMeta) {
		this.instanceMeta = instanceMeta;
	}
	
	
}
