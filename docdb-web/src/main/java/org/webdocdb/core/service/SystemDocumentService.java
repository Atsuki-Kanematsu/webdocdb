package org.webdocdb.core.service;

import java.util.List;

import org.springframework.data.mongodb.core.query.Query;
import org.webdocdb.core.document.SystemDocument;

public abstract class SystemDocumentService<D extends SystemDocument> extends DocumentService<D> {

	protected D findById(String id) {
		Class<D> clazz = getGenericType();
		String dbCollectionName = clazz.getSimpleName();
		return super.findById(dbCollectionName, id);
	}
	
	protected D findOne(Query query) {
		Class<D> clazz = getGenericType();
		String dbCollectionName = clazz.getSimpleName();
		return super.findOne(dbCollectionName, query);
	}
	
	protected List<D> find(Query query) {
		Class<D> clazz = getGenericType();
		String dbCollectionName = clazz.getSimpleName();
		return super.find(dbCollectionName, query);
	}
	
	protected void insert(D document, int collectionType) {
		Class<D> clazz = getGenericType();
		String dbCollectionName = clazz.getSimpleName();
		super.insert(dbCollectionName, collectionType, document);
	}
	
	protected void update(D document) {
		Class<D> clazz = getGenericType();
		String dbCollectionName = clazz.getSimpleName();
		super.update(dbCollectionName, document);
	}

	protected void delete(D document) {
		Class<D> clazz = getGenericType();
		String dbCollectionName = clazz.getSimpleName();
		super.delete(dbCollectionName, document);
	}

	protected void delete(String documentId) {
		Class<D> clazz = getGenericType();
		String dbCollectionName = clazz.getSimpleName();
		super.delete(dbCollectionName, documentId);
	}

}
