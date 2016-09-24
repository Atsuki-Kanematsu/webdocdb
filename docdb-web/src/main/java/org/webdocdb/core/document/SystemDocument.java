package org.webdocdb.core.document;

public abstract class SystemDocument extends AbstractDocument implements Document {

	private String instanceId;

	public String getInstanceId() {
		return instanceId;
	}

	public void setInstanceId(String instanceId) {
		this.instanceId = instanceId;
	}
	
	
}
