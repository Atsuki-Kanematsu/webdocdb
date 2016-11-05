package org.webdocdb.core.accessor;

import java.util.List;

import org.springframework.data.mongodb.core.query.Query;
import org.webdocdb.core.document.Document;

public interface DocumentFinder<D extends Document> {
	
	D findOne(String dbCollectionName, Query query);
	
	List<D> find(String dbCollectionName, Query query);
	
	long count(String dbCollectionName, Query query);
	
	boolean exists(String dbCollectionName, Query query);

}
