package org.webdocdb.tools.cleaner;

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
			if (args.length == 0) {
				System.out.println("インスタンス名を指定してください");
				System.exit(-1);
			}
			ApplicationContext context = SpringApplication.run(InstanceCleaner.class, new String[0]);
			InstanceManager instanceManager = context.getBean(InstanceManager.class);
			if (!instanceManager.exists(args[0])) {
				System.out.println(String.format("インスタンス[%s]が見つかりません", args[0]));
				System.exit(-1);
			}
			System.out.println(String.format("インスタンス[%s]の削除を行います", args[0]));
			instanceManager.drop(args[0]);
			System.out.println("インスタンスの削除が終了しました");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
