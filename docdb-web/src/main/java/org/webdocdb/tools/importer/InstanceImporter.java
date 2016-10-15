package org.webdocdb.tools.importer;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.EmbeddedServletContainerAutoConfiguration;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.webdocdb.core.manager.InstanceManager;

@SpringBootApplication(exclude = {EmbeddedServletContainerAutoConfiguration.class, WebMvcAutoConfiguration.class})
@ComponentScan({"org.webdocdb.generator", "org.webdocdb.core"})
public class InstanceImporter {

	public static void main(String[] args) throws IOException {
		try {
			ApplicationContext context = SpringApplication.run(InstanceImporter.class, new String[0]);
			InstanceManager instanceManager = context.getBean(InstanceManager.class);
			instanceManager.drop(args[0]);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			System.exit(0);
		}
	}

}
