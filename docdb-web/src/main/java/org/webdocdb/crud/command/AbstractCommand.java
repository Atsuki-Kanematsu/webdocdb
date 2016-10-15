package org.webdocdb.crud.command;

public class AbstractCommand {
	
	public static enum RedirectMode {
		NONE,
		WRITE,
		APPEND
	}
	
	private String command;
	private String[] args;
	private RedirectMode redirectMode;
	private String redirectPath;
	
	public AbstractCommand(String commandLine) {
		commandLine = cutAndParseRedirect(commandLine);
	}
	
	private String cutAndParseRedirect(String commandLine) {
		redirectMode = RedirectMode.NONE;
		int pos = commandLine.indexOf(">");
		if (pos == -1) {
			return commandLine;
		}
		String redirectText = commandLine.substring(pos);
		redirectMode = RedirectMode.WRITE;
		if (redirectText.startsWith(">>")) {
			redirectMode = RedirectMode.APPEND;
		}
		return commandLine.substring(0, pos);
	}
}
