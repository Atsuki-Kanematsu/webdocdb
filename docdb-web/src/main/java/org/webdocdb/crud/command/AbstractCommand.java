package org.webdocdb.crud.command;

public class AbstractCommand {
	
	public static enum RedirectMode {
		NONE,
		WRITE,
		APPEND
	}
	
	private static final String LINE_SEPARATOR = "\n";
	
	private String command;
	private String[] args;
	private RedirectMode redirectMode;
	private String redirectPath;
	
	public AbstractCommand(String commandLine) {
		commandLine = cutAndParseRedirect(commandLine.trim());
		int pos = commandLine.indexOf(" ");
		if (pos == -1) {
			command = commandLine;
			args = new String[0];
		} else {
			command = commandLine.substring(0, pos);
			args = command.substring(pos + 1).split(" ");
		}
	}
	
	private String cutAndParseRedirect(String commandLine) {
		redirectMode = RedirectMode.NONE;
		int pos = commandLine.indexOf(">");
		if (pos == -1) {
			return commandLine;
		}
		String redirectText = commandLine.substring(pos);
		if (redirectText.startsWith(">>")) {
			redirectMode = RedirectMode.APPEND;
			redirectPath = redirectText.substring(2).trim();
		} else {
			redirectMode = RedirectMode.WRITE;
			redirectPath = redirectText.substring(1).trim();
		}
		return commandLine.substring(0, pos).trim();
	}

	public String getCommand() {
		return command;
	}

	public String[] getArgs() {
		return args;
	}

	public RedirectMode getRedirectMode() {
		return redirectMode;
	}

	public String getRedirectPath() {
		return redirectPath;
	}
	
	protected void printConsole(String text) {
		System.out.print(text);
	}
	
	protected void printConsoleLine(String log) {
		printConsole(log + LINE_SEPARATOR);
	}
}
