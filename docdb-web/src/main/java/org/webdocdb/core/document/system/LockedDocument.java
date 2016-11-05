package org.webdocdb.core.document.system;

import org.webdocdb.core.document.AbstractDocument;
import org.webdocdb.core.document.annotation.Index;
import org.webdocdb.core.document.annotation.IndexField;
import org.webdocdb.core.document.annotation.Indexes;
import org.webdocdb.core.document.annotation.PrimaryKey;
import org.webdocdb.core.document.annotation.IndexField.IndexOrder;

@Indexes({
	@Index(fields={@IndexField(name="instanceId", order=IndexOrder.ASC)}, unique=true),
	@Index(fields={@IndexField(name="transactionId", order=IndexOrder.ASC)}, unique=true),
})
public class LockedDocument extends AbstractDocument {

	@PrimaryKey
	private String documentId;
	private String instanceId;
	private String transactionId;
	private String transactorId;
	private String collectionId;
	
	public String getDocumentId() {
		return documentId;
	}
	public void setDocumentId(String documentId) {
		this.documentId = documentId;
	}
	public String getInstanceId() {
		return instanceId;
	}
	public void setInstanceId(String instanceId) {
		this.instanceId = instanceId;
	}
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	public String getTransactorId() {
		return transactorId;
	}
	public void setTransactorId(String transactorId) {
		this.transactorId = transactorId;
	}
	public String getCollectionId() {
		return collectionId;
	}
	public void setCollectionId(String collectionId) {
		this.collectionId = collectionId;
	}

	
}
