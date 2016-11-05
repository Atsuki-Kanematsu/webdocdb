package org.webdocdb.tools.crud;

import org.webdocdb.core.document.management.Account;

public class CommandRegistry {

	private boolean isConnected = false;
	private Account connectedAccount = null;
	
	public void reset() {
		isConnected = false;
		connectedAccount = null;
	}
	
	public boolean isConnected() {
		return isConnected;
	}
	public void setConnected(boolean isConnected) {
		this.isConnected = isConnected;
	}
	public Account getConnectedAccount() {
		return connectedAccount;
	}
	public void setConnectedAccount(Account connectedAccount) {
		this.connectedAccount = connectedAccount;
	}
}
