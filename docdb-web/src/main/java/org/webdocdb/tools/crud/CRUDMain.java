package org.webdocdb.tools.crud;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.webdocdb.core.util.StringUtil;
import org.webdocdb.tools.crud.command.AbstractCommand;
import org.webdocdb.tools.crud.command.ConnectCommand;
import org.webdocdb.tools.crud.command.GetCommand;
import org.webdocdb.tools.generator.InstanceGenerator;

public class CRUDMain {

	private static Map<String, Class<? extends AbstractCommand>> commands;
	
	static {
		commands = new HashMap<>();
		// common commands
		commands.put("connect", ConnectCommand.class);
		commands.put("disconnect", GetCommand.class);
		// transaction commands
		commands.put("begin-transaction", GetCommand.class);
		commands.put("rollback-transaction", GetCommand.class);
		commands.put("commit-transaction", GetCommand.class);
		// Collection manipulate commands.
		commands.put("list-collection", GetCommand.class);
		commands.put("remove-collection", GetCommand.class);
		commands.put("clear-collection", GetCommand.class);
		commands.put("define-collection", GetCommand.class);
		commands.put("disable-collection", GetCommand.class);
		commands.put("enable-collection", GetCommand.class);
		// Document manipulate commands.
		commands.put("get-document", GetCommand.class);
		commands.put("create-document", GetCommand.class);
		commands.put("modify-document", GetCommand.class);
		commands.put("remove-document", GetCommand.class);
		commands.put("save-document", GetCommand.class);
		commands.put("find-document", GetCommand.class);
		commands.put("tree-document", GetCommand.class);
		commands.put("move-document", GetCommand.class);
		
	}
	
	public static void main(String[] args) {
		try {
			CommandRegistry registery = new CommandRegistry();
			ApplicationContext context = SpringApplication.run(InstanceGenerator.class, new String[0]);
			try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
				while(true) {
					String line = reader.readLine();
					if (StringUtil.isEmpty(line)) {
						continue;
					}
					line = line.trim();
					if (line.toLowerCase().equals("exit")) {
						break;
					}
					String commandName = line.split(" ")[0];
					if (!commands.containsKey(commandName.toLowerCase())) {
						System.out.println("コマンドが間違っています");
						continue;
					}
					Class<? extends AbstractCommand> clazz = commands.get(commandName);
					Constructor<? extends AbstractCommand> constructor = clazz.getDeclaredConstructor(String.class);
					AbstractCommand command = constructor.newInstance(line);
					if (!command.execute(registery)) {
						continue;
					}
				}
			}
			System.exit(0);
		} catch (Exception ex) {
			ex.printStackTrace();
			System.exit(-1);
		}

	}
}
