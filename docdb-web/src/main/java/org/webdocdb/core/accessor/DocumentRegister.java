package org.webdocdb.core.accessor;

import java.util.List;

import org.springframework.data.mongodb.core.query.Query;
import org.webdocdb.core.document.Document;
import org.webdocdb.core.document.content.DocumentContent;

public interface DocumentRegister<D extends Document> {

	D create(String dbCollectionName, DocumentContent content);

	D replaceFirst(String dbCollectionName, Query query, DocumentContent content);
	
	List<D> replace(String dbCollectionName, Query query, DocumentContent content);

	void removeFirst(String dbCollectionName, Query query);

	void remove(String dbCollectionName, Query query);

}
