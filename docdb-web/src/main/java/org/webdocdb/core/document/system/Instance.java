package org.webdocdb.core.document.system;

import java.util.Map;

import org.springframework.data.mongodb.core.mapping.Field;
import org.webdocdb.core.document.AbstractDocument;
import org.webdocdb.core.document.annotation.Index;
import org.webdocdb.core.document.annotation.IndexField;
import org.webdocdb.core.document.annotation.Indexes;
import org.webdocdb.core.document.annotation.PrimaryKey;
import org.webdocdb.core.document.annotation.IndexField.IndexOrder;
import org.webdocdb.core.document.content.DocumentContent;

@Indexes({
	@Index(fields={@IndexField(name="instanceId", order=IndexOrder.ASC)}, unique=true),
})
public class Instance extends AbstractDocument {

	@PrimaryKey
	@Field("instanceId")
	private String instanceId;
	private String instanceName;
	private DocumentContent instanceMeta;
	private Map<String, InstanceParameter> parameters;
	
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
	public Map<String, InstanceParameter> getParameters() {
		return parameters;
	}
	public void setParameters(Map<String, InstanceParameter> parameters) {
		this.parameters = parameters;
	}


	public static class InstanceParameter {
		
		private String parameterName;
		private String parameterValue;
		private String description;
		
		public InstanceParameter(String name, String value, String description) {
			this.parameterName = name;
			this.parameterValue = value;
			this.description = description;
		}
		public String getParameterName() {
			return parameterName;
		}
		public void setParameterName(String parameterName) {
			this.parameterName = parameterName;
		}
		public String getParameterValue() {
			return parameterValue;
		}
		public void setParameterValue(String parameterValue) {
			this.parameterValue = parameterValue;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		
	}
}
