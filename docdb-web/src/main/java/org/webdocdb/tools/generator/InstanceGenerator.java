package org.webdocdb.tools.generator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.EmbeddedServletContainerAutoConfiguration;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.webdocdb.core.util.StringUtil;
import org.webdocdb.tools.generator.step.Question;
import org.webdocdb.tools.generator.step.Question.ValidationResult;
import org.webdocdb.tools.generator.step.instance.InstanceNameQuestion;

@SpringBootApplication(exclude = {EmbeddedServletContainerAutoConfiguration.class, WebMvcAutoConfiguration.class})
@ComponentScan({"org.webdocdb.generator", "org.webdocdb.core"})
public class InstanceGenerator {

	public static void main(String[] args) throws IOException {
		try {
			ApplicationContext context = SpringApplication.run(InstanceGenerator.class, new String[0]);

			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			Question question = context.getBean(InstanceNameQuestion.class);
			while (true) {
				String text = question.getText();
				System.out.print(String.format("%s>", text));
				String line = in.readLine();
				ValidationResult result = question.validate(line);
				if (!result.isResult()) {
					String answer = result.getInvalidAnswer();
					if (!StringUtil.isEmpty(answer)) {
						System.out.println(result.getInvalidAnswer());
					}
					continue;
				}
				question.process(line);
				if (!question.hasNext()) {
					break;
				}
				question = question.next();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			System.exit(0);
		}
	}

}
