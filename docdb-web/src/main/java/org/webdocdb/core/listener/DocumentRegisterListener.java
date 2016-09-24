package org.webdocdb.core.listener;

import org.springframework.data.mongodb.core.query.Query;
import org.webdocdb.core.document.Document;

public interface DocumentRegisterListener {

	public void beforeInsert(Object document);
	
	public void afterInsert(Object document);
	
	public void beforeUpdate(Query query, Object before, Object after);
	
	public void afterUpdate(Query query, Object after);
	
	public void beforeDelete(Query query, Document document);
	
	public void afterDelete(Query query, Document document);
	
}
