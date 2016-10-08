package org.webdocdb.core.document.system;

import org.webdocdb.core.document.ManagementDocument;
import org.webdocdb.core.document.annotation.Index;
import org.webdocdb.core.document.annotation.IndexField;
import org.webdocdb.core.document.annotation.Indexes;

@Indexes({
	@Index(fields={@IndexField(name="collectionId")},unique=true),
	@Index(fields={@IndexField(name="collectionName")}),
	@Index(fields={@IndexField(name="collectionType")}),
})
public class Collection extends ManagementDocument {

	public static final int DATA_COLLECTION = 0;
	public static final int QUEUE_COLLECTION = 1;
	public static final int FILE_COLLECTION = 2;
	public static final int SYSTEM_COLLECTION = 9;
	
	private String collectionId;
	private String collectionName;
	private int collectionType;
	
	public String getCollectionId() {
		return collectionId;
	}
	public void setCollectionId(String collectionId) {
		this.collectionId = collectionId;
	}
	public String getCollectionName() {
		return collectionName;
	}
	public void setCollectionName(String collectionName) {
		this.collectionName = collectionName;
	}
	public int getCollectionType() {
		return collectionType;
	}
	public void setCollectionType(int collectionType) {
		this.collectionType = collectionType;
	}
	
	
}
