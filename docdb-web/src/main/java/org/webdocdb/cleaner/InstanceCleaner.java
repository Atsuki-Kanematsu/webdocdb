package org.webdocdb.cleaner;

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
public class InstanceCleaner {

	public static void main(String[] args) throws IOException {
		try {
			ApplicationContext context = SpringApplication.run(InstanceCleaner.class, new String[0]);
			InstanceManager instanceManager = context.getBean(InstanceManager.class);
			instanceManager.drop(args[0]);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			System.exit(0);
		}
	}

}
