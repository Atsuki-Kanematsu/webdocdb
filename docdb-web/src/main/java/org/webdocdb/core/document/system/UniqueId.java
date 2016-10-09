package org.webdocdb.core.document.system;

import org.webdocdb.core.document.AbstractDocument;
import org.webdocdb.core.document.annotation.Index;
import org.webdocdb.core.document.annotation.IndexField;
import org.webdocdb.core.document.annotation.Indexes;

@Indexes({
	@Index(fields={@IndexField(name="uniqueId")}, unique=true),
	@Index(fields={@IndexField(name="idType")}),
})
public class UniqueId extends AbstractDocument {

	public static final int ID_TYPE_COLLECTION = 0;
	public static final int ID_TYPE_INSTANCE = 1;
	public static final int ID_TYPE_ACCOUNT_AND_GROUP = 2;
	public static final int ID_TYPE_MESSAGE = 3;
	public static final int ID_TYPE_DOCUMENT = 5;
	
	private String uniqueId;
	private String instanceId;
	private int idType;
	
	public String getUniqueId() {
		return uniqueId;
	}
	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
	}
	
	public String getInstanceId() {
		return instanceId;
	}
	public void setInstanceId(String instanceId) {
		this.instanceId = instanceId;
	}
	public int getIdType() {
		return idType;
	}
	public void setIdType(int idType) {
		this.idType = idType;
	}
	
	
	
}
