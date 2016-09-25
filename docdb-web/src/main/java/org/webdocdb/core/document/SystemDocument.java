package org.webdocdb.core.document;

import org.springframework.data.mongodb.core.mapping.Field;

public abstract class SystemDocument extends AbstractDocument {

	@Field
	private String instanceId;

	public String getInstanceId() {
		return instanceId;
	}

	public void setInstanceId(String instanceId) {
		this.instanceId = instanceId;
	}
	
}
