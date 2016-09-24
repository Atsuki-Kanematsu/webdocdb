package org.webdocdb.core.listener;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;
import org.webdocdb.core.document.Document;

@Component
@Scope("singleton")
public class DocumentListenerFactory {

	private List<DocumentSelectListener> selectListeners = new ArrayList<>();
	private List<DocumentRegisterListener> registerListeners = new ArrayList<>();

	public int getSelectListenerSize() {
		return selectListeners.size();
	}
	public int getRegisterListenerSize() {
		return registerListeners.size();
	}

	public DocumentSelectListener getSelectListener(int index) {
		return selectListeners.get(index);
	}
	public DocumentRegisterListener getRegisterListener(int index) {
		return registerListeners.get(index);
	}
	

	public <D extends Document> void callBeforeFind(Query query, Class<D> documentClass) {
		for (DocumentSelectListener listener : selectListeners) {
			listener.beforeFind(query, documentClass);
		}
	}
	
	public <D extends Document> void callAfterFind(Query query, List<D> foundDocuments, Class<D> documentClass) {
		for (DocumentSelectListener listener : selectListeners) {
			listener.afterFind(query, foundDocuments, documentClass);
		}
	}
	public <D extends Document> void callBeforeFindOne(Query query, Class<D> documentClass) {
		for (DocumentSelectListener listener : selectListeners) {
			listener.beforeFindOne(query, documentClass);
		}
	}
	
	public <D extends Document> void callAfterFindOne(Query query, D foundDocument, Class<D> documentClass) {
		for (DocumentSelectListener listener : selectListeners) {
			listener.afterFindOne(query, foundDocument, documentClass);
		}
	}
	
	public void callBeforeInsert(Document document) {
		for (DocumentRegisterListener listener : registerListeners) {
			listener.beforeInsert(document);
		}
	}
	public void callAfterInsert(Document document) {
		for (DocumentRegisterListener listener : registerListeners) {
			listener.afterInsert(document);
		}
	}
	public void callBeforeUpdate(Query query, Document before, Document after) {
		for (DocumentRegisterListener listener : registerListeners) {
			listener.beforeUpdate(query, before, after);
		}
	}
	public void callAfterUpdate(Query query, Document after) {
		for (DocumentRegisterListener listener : registerListeners) {
			listener.afterUpdate(query, after);
		}
	}
	public void callBeforeDelete(Query query, Document document){
		for (DocumentRegisterListener listener : registerListeners) {
			listener.beforeDelete(query, document);
		}
	}
	public void callAfterDelete(Query query, Document document) {
		for (DocumentRegisterListener listener : registerListeners) {
			listener.afterDelete(query, document);
		}
	}
}
