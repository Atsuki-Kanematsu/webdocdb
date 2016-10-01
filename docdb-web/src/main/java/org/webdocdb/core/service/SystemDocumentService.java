package org.webdocdb.core.service;

import java.util.List;

import org.springframework.data.mongodb.core.query.Query;
import org.webdocdb.core.document.SystemDocument;

public abstract class SystemDocumentService<D extends SystemDocument> extends DocumentService<D> {

	protected D findById(String id) {
		Class<D> clazz = getGenericType();
		String collectionName = clazz.getSimpleName();
		return super.findById(collectionName, id);
	}
	
	protected D findOne(Query query) {
		Class<D> clazz = getGenericType();
		String collectionName = clazz.getSimpleName();
		return super.findOne(collectionName, query);
	}
	
	protected List<D> find(Query query) {
		Class<D> clazz = getGenericType();
		String collectionName = clazz.getSimpleName();
		return super.find(collectionName, query);
	}
	
	protected void insert(D document, int collectionType) {
		Class<D> clazz = getGenericType();
		String collectionName = clazz.getSimpleName();
		super.insert(collectionName, collectionType, document);
	}
	
	protected void update(D document) {
		Class<D> clazz = getGenericType();
		String collectionName = clazz.getSimpleName();
		super.update(collectionName, document);
	}

	protected void delete(D document) {
		Class<D> clazz = getGenericType();
		String collectionName = clazz.getSimpleName();
		super.delete(collectionName, document);
	}

	protected void delete(String documentId) {
		Class<D> clazz = getGenericType();
		String collectionName = clazz.getSimpleName();
		super.delete(collectionName, documentId);
	}

}
