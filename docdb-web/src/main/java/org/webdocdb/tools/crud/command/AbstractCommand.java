package org.webdocdb.tools.crud.command;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.webdocdb.tools.crud.CommandRegistry;
import org.webdocdb.tools.crud.exception.InvalidArgumentException;

public abstract class AbstractCommand {
	
	public static enum RedirectMode {
		NONE,
		WRITE,
		APPEND
	}
	
	private static final String LINE_SEPARATOR = "\n";
	
	private RedirectMode redirectMode;
	private String redirectPath;
	
	protected abstract Set<CommandSwitchDef> defineAllowSwitch() ;
	
	public abstract boolean execute(CommandRegistry registery);
	
	public AbstractCommand(String commandLine) throws InvalidArgumentException {
		Set<CommandSwitchDef> defSet = defineAllowSwitch();
		List<CommandSwitch> switchList = parseCommand(commandLine);
		if (!checkUnsupportSwitch(switchList, defSet)) {
			throw new InvalidArgumentException("");
		}
		if (!checkRequiredSwitch(switchList, defSet)) {
			throw new InvalidArgumentException("");
		}
	}
	
	
	private List<CommandSwitch> parseCommand(String commandList) {
		return null;
	}
	
	private boolean checkUnsupportSwitch(List<CommandSwitch> switchList, Set<CommandSwitchDef> defSet) {
		return true;
	}
	
	private boolean checkRequiredSwitch(List<CommandSwitch> switchList, Set<CommandSwitchDef> defSet) {
		return true;
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
	
	public static class CommandSwitchDef {
		private String commandSwitch;
		private boolean needValue;
		private boolean required;
		
		public CommandSwitchDef(String commandSwitch, boolean needValue, boolean required) {
			this.commandSwitch = commandSwitch;
			this.needValue = needValue;
			this.required = required;
		}
		public String getCommandSwitch() {
			return commandSwitch;
		}
		public boolean isNeedValue() {
			return needValue;
		}
		public boolean isRequired() {
			return required;
		}
		
	}
	
	public static class CommandSwitch {
		private String commandSwitch;
		private String switchValue;
		public CommandSwitch(String commandSwitch, String switchValue) {
			this.commandSwitch = commandSwitch;
			this.switchValue = switchValue;
		}
		public String getCommandSwitch() {
			return commandSwitch;
		}
		public String getSwitchValue() {
			return switchValue;
		}
	}
}
