package org.webdocdb.core.document.system;

import org.webdocdb.core.document.SystemDocument;

public class UniqueId extends SystemDocument {

	public static final int ID_TYPE_COLLECTION = 0;
	public static final int ID_TYPE_INSTANCE = 1;
	public static final int ID_TYPE_ACCOUNT_AND_GROUP = 2;
	public static final int ID_TYPE_MESSAGE = 3;
	public static final int ID_TYPE_DOCUMENT = 5;
	
	
	
	private String uniqueId;
	private int idType;
	
	public String getUniqueId() {
		return uniqueId;
	}
	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
	}
	public int getIdType() {
		return idType;
	}
	public void setIdType(int idType) {
		this.idType = idType;
	}
	
	
	
}
