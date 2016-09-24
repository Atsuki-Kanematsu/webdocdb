package org.webdocdb.core.service.system;

import org.webdocdb.core.document.Document;
import org.webdocdb.core.document.DocumentContent;
import org.webdocdb.core.document.system.Collection;
import org.webdocdb.core.document.system.Group;
import org.webdocdb.core.service.SystemDocumentService;

public class GroupService extends SystemDocumentService<Group> {

	public Group findById(String groupId) {
		return super.findById(groupId);
	}
	
	public void create(Group group) {
		super.insert(group, Collection.SYSTEM_COLLECTION);
	}
	
	public Group create(String groupName) {
		return this.create(groupName, null);
	}
	
	public Group create(String groupName, DocumentContent meta) {
		Group group = new Group();
		group.setGroupName(groupName);
		group.setGroupMeta(meta);
		this.create(group);
		return group;
	}
	
	public void modify(Group group) {
		super.update(group);
	}
	
	public void remove(String groupId) {
		super.delete(groupId);
	}
	
	public void disable(String groupId) {
		Group group = this.findById(groupId);
		group.setStatus(Document.STATUS_DISABLE);
		modify(group);
	}
	
	public void enable(String groupId) {
		Group group = this.findById(groupId);
		group.setStatus(Document.STATUS_ENABLE);
		modify(group);
	}
}
