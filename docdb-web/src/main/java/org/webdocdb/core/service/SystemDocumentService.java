package org.webdocdb.core.service;

import java.util.List;

import org.springframework.data.mongodb.core.query.Query;
import org.webdocdb.core.document.SystemDocument;
import org.webdocdb.core.document.system.Collection;

public abstract class SystemDocumentService<D extends SystemDocument> extends DocumentService<D> {

	protected D findById(String id) {
		Class<D> clazz = getGenericType();
		String collectionName = clazz.getSimpleName();
		return super.findById(collectionName, Collection.SYSTEM_COLLECTION, id);
	}
	
	protected D findOne(Query query) {
		Class<D> clazz = getGenericType();
		String collectionName = clazz.getSimpleName();
		return super.findOne(collectionName, Collection.SYSTEM_COLLECTION, query);
	}
	
	protected List<D> find(Query query) {
		Class<D> clazz = getGenericType();
		String collectionName = clazz.getSimpleName();
		return super.find(collectionName, Collection.SYSTEM_COLLECTION, query);
	}
	
	protected void insert(D document) {
		Class<D> clazz = getGenericType();
		String collectionName = clazz.getSimpleName();
		super.insert(collectionName, Collection.SYSTEM_COLLECTION, document);
	}
	
	protected void update(D document) {
		Class<D> clazz = getGenericType();
		String collectionName = clazz.getSimpleName();
		super.update(collectionName, Collection.SYSTEM_COLLECTION, document);
	}

	protected void delete(D document) {
		Class<D> clazz = getGenericType();
		String collectionName = clazz.getSimpleName();
		super.delete(collectionName, Collection.SYSTEM_COLLECTION, document);
	}

	protected void delete(String documentId) {
		Class<D> clazz = getGenericType();
		String collectionName = clazz.getSimpleName();
		super.delete(collectionName, Collection.SYSTEM_COLLECTION, documentId);
	}

}
