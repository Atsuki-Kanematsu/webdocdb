package org.webdocdb.core.listener;

import org.webdocdb.core.document.system.Collection;

public interface CollectionListener {

	public void beforeCreate(String collectionName);

	public void afterCreate(Collection collection);

	public void beforeRemove(Collection collection);

	public void afterRemove(Collection collection);
}
