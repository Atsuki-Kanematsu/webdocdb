package org.webdocdb.core.document;

import org.springframework.data.mongodb.core.mapping.Field;

public abstract class ManagementDocument extends AbstractDocument {

	@Field("instanceId")
	private String instanceId;

	public String getInstanceId() {
		return instanceId;
	}

	public void setInstanceId(String instanceId) {
		this.instanceId = instanceId;
	}
	
}
