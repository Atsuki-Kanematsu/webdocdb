package org.webdocdb.core.listener;

import org.springframework.data.mongodb.core.query.Query;
import org.webdocdb.core.document.Document;

public interface DocumentRegisterListener {

	public void beforeInsert(Document document);
	
	public void afterInsert(Document document);
	
	public void beforeUpdate(Query query, Document before, Document after);
	
	public void afterUpdate(Query query, Document after);
	
	public void beforeDelete(Query query, Document document);
	
	public void afterDelete(Query query, Document document);
	
}
