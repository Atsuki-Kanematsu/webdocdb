package org.webdocdb.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"org.webdocdb.web", "org.webdocdb.core"})
public class DocdbWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(DocdbWebApplication.class, args);
	}
}
