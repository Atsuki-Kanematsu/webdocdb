package org.webdocdb.core.listener;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.webdocdb.core.document.system.Collection;

@Component
@Scope("singleton")
public class CollectionListenerFactory {

	private List<CollectionListener> collectionListeners = new ArrayList<>();
	
	public int getRegisterListenerSize() {
		return collectionListeners.size();
	}
	
	public CollectionListener getRegisterListener(int index) {
		return collectionListeners.get(index);
	}
	
	public void callBeforeInsert(String collectionName) {
		for (CollectionListener listener : collectionListeners) {
			listener.beforeCreate(collectionName);
		}
	}
	public void callAfterInsert(Collection collection) {
		for (CollectionListener listener : collectionListeners) {
			listener.afterCreate(collection);
		}
	}
	public void callBeforeUpdate(Collection collection) {
		for (CollectionListener listener : collectionListeners) {
			listener.beforeRemove(collection);
		}
	}
	public void callAfterUpdate(Collection collection) {
		for (CollectionListener listener : collectionListeners) {
			listener.afterRemove(collection);
		}
	}
}
