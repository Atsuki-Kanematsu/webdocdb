package org.webdocdb.tools.crud.command;

import java.util.HashSet;
import java.util.Set;

import org.webdocdb.tools.crud.CommandRegistry;
import org.webdocdb.tools.crud.exception.InvalidArgumentException;

public class ConnectCommand extends AbstractCommand {

	public ConnectCommand(String commandLine) throws InvalidArgumentException {
		super(commandLine);
	}
	
	@Override
	protected Set<CommandSwitchDef> defineAllowSwitch() {
		Set<CommandSwitchDef> switchSet = new HashSet<>();
		switchSet.add(new CommandSwitchDef("instance", true, true));
		switchSet.add(new CommandSwitchDef("id", true, true));
		switchSet.add(new CommandSwitchDef("password", true, true));
		return switchSet;
	}


	@Override
	public boolean execute(CommandRegistry registery) {
		// TODO Auto-generated method stub
		return false;
	}

}
