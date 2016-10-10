package org.webdocdb.cleaner;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.EmbeddedServletContainerAutoConfiguration;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.core.MongoOperations;

@SpringBootApplication(exclude = {EmbeddedServletContainerAutoConfiguration.class, WebMvcAutoConfiguration.class})
@ComponentScan({"org.webdocdb.generator", "org.webdocdb.core"})
public class MongoInitializer {

	public static void main(String[] args) throws IOException {
		try {
			ApplicationContext context = SpringApplication.run(MongoInitializer.class, new String[0]);
			MongoOperations mongo = context.getBean(MongoOperations.class);
			for (String name : mongo.getCollectionNames()) {
				mongo.dropCollection(name);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			System.exit(0);
		}
	}

}
