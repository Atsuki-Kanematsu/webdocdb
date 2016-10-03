package org.webdocdb.core.listener;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;
import org.webdocdb.core.document.Document;
import org.webdocdb.core.listener.initial.IdGenerator;

@Component
@Scope("singleton")
public class DocumentListenerFactory {

	private List<Object> selectListenerList;
	private List<Object> registerListenerList;
	

	@Autowired
	protected ApplicationContext context;
	
	@PostConstruct
	public void postConstruct() {
		selectListenerList = new ArrayList<>();
		registerListenerList = new ArrayList<>();
		selectListenerList.add(IdGenerator.class);
	}
	
	protected void invokeListenerMethod(Object target, String methodName, Class<?>[] argClasses, Object args) {
		Object listener = null;
		if (target instanceof Class) {
			listener = context.getBean((Class<?>) target);
		} else {
			listener = target;
		}
		if (listener == null) {
			return;
		}
		Method method;
		try {
			method = listener.getClass().getDeclaredMethod(methodName, argClasses);
			method.invoke(listener, args);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	
	public <D extends Document> void callBeforeFind(Query query, Class<D> documentClass) {
		for (Object listenerDef : selectListenerList) {
			invokeListenerMethod(
					listenerDef, 
					"beforeFind", 
					new Class[] {Query.class, Class.class},
					new Object[] {query, documentClass});
		}
	}
	
	public <D extends Document> void callAfterFind(Query query, List<D> foundDocuments, Class<D> documentClass) {
		for (Object listenerDef : selectListenerList) {
			invokeListenerMethod(
					listenerDef, 
					"afterFind", 
					new Class[] {Query.class, List.class, Class.class},
					new Object[] {query, foundDocuments, documentClass});
		}
	}
	
	public <D extends Document> void callBeforeFindOne(Query query, Class<D> documentClass) {
		for (Object listenerDef : selectListenerList) {
			invokeListenerMethod(
					listenerDef, 
					"beforeFindOne", 
					new Class[] {Query.class, Class.class},
					new Object[] {query, documentClass});
		}
	}
	
	public <D extends Document> void callAfterFindOne(Query query, D foundDocument, Class<D> documentClass) {
		for (Object listenerDef : selectListenerList) {
			invokeListenerMethod(
					listenerDef, 
					"afterFind", 
					new Class[] {Query.class, Document.class, Class.class},
					new Object[] {query, foundDocument, documentClass});
		}
	}
	
	public void callBeforeInsert(Document document) {
		for (Object listenerDef : registerListenerList) {
			invokeListenerMethod(
					listenerDef, 
					"beforeInsert", 
					new Class[] {Document.class},
					new Object[] {document});
		}
	}
	public void callAfterInsert(Document document) {
		for (Object listenerDef : registerListenerList) {
			invokeListenerMethod(
					listenerDef, 
					"afterInsert", 
					new Class[] {Document.class},
					new Object[] {document});
		}
	}
	public void callBeforeUpdate(Query query, Document before, Document after) {
		for (Object listenerDef : registerListenerList) {
			invokeListenerMethod(
					listenerDef, 
					"beforeUpdate", 
					new Class[] {Query.class, Document.class, Document.class},
					new Object[] {query, before, after});
		}
	}
	public void callAfterUpdate(Query query, Document after) {
		for (Object listenerDef : registerListenerList) {
			invokeListenerMethod(
					listenerDef, 
					"afterUpdate", 
					new Class[] {Query.class, Document.class},
					new Object[] {query, after});
		}
	}
	public void callBeforeDelete(Query query, Document document){
		for (Object listenerDef : registerListenerList) {
			invokeListenerMethod(
					listenerDef, 
					"beforeDelete", 
					new Class[] {Query.class, Document.class},
					new Object[] {query, document});
		}
	}
	public void callAfterDelete(Query query, Document document) {
		for (Object listenerDef : registerListenerList) {
			invokeListenerMethod(
					listenerDef, 
					"afterDelete", 
					new Class[] {Query.class, Document.class},
					new Object[] {query, document});
		}
	}
	
	
}
