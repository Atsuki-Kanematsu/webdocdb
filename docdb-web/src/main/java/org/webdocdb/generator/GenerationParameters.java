package org.webdocdb.generator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.webdocdb.core.document.management.Account;
import org.webdocdb.core.document.management.Group;
import org.webdocdb.core.document.management.Message;
import org.webdocdb.core.document.system.Instance;

@Component
@Scope("singleton")
public class GenerationParameters {

	private Instance instance = new Instance();
	private List<Account> accounts = new ArrayList<>();
	private List<Group> groups = new ArrayList<>();
	private List<Message> messages = new ArrayList<>();
	
	public Instance getInstance() {
		return instance;
	}
	public void setInstance(Instance instance) {
		this.instance = instance;
	}
	public List<Group> getGroups() {
		return groups;
	}
	public void setGroups(List<Group> groups) {
		this.groups = groups;
	}
	public List<Account> getAccounts() {
		return accounts;
	}
	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}
	public List<Message> getMessages() {
		return messages;
	}
	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}
	

	
	
	
}
