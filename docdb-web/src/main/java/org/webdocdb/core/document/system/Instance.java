package org.webdocdb.core.document.system;

import org.webdocdb.core.document.DocumentContent;
import org.webdocdb.core.document.SystemDocument;

public class Instance extends SystemDocument {

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
