package org.webdocdb.core.listener;

import java.util.List;

import org.springframework.data.mongodb.core.query.Query;
import org.webdocdb.core.document.Document;

public interface DocumentSelectListener {

	public <D extends Document> void beforeFind(Query query, Class<D> documentClass);

	public <D extends Document> void afterFind(Query query, List<D> foundDocuments, Class<D> documentClass);
	
	public <D extends Document> void beforeFindOne(Query query, Class<D> documentClass);
	
	public <D extends Document> void afterFindOne(Query idQuery,  D foundDocument, Class<D> documentClass);
	
	
}
