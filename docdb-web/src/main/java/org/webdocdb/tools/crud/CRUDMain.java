package org.webdocdb.tools.crud;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.webdocdb.core.util.StringUtil;
import org.webdocdb.tools.generator.InstanceGenerator;

public class CRUDMain {

	public static void main(String[] args) {
		try {
			ApplicationContext context = SpringApplication.run(InstanceGenerator.class, new String[0]);
			try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
				while(true) {
					String line = reader.readLine();
					if (StringUtil.isEmpty(line)) {
						continue;
					}
					if (line.toLowerCase().equals("exit")) {
						break;
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
