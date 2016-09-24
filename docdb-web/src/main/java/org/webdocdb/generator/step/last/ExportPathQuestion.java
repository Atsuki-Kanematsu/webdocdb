package org.webdocdb.generator.step.last;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.webdocdb.core.util.StringUtil;
import org.webdocdb.generator.GenerationParameters;
import org.webdocdb.generator.step.Question;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class ExportPathQuestion implements Question {

	private static final String DEFAULT_DIR = "./";
	private static final String DEFAULT_NAME = "instance.settings.json";
	
	@Autowired
	private GenerationParameters params;
	
	@Autowired
	protected GenerateQuestion nextQuestion;
	
	private File exportFile;
	
	@Override
	public String getText() {
		return "export-path(default:'./instance.settings.json')";
	}

	@Override
	public ValidationResult validate(String value) {
		if (StringUtil.isEmpty(value)) {
			value = DEFAULT_DIR + DEFAULT_NAME;
		}
		File file = new File(value);
		if (file.isDirectory()) {
			if (!file.exists()) {
				return new ValidationResult(false, "パスの指定が誤っています");
			}
			file = new File(file, DEFAULT_NAME);
		}
		exportFile = file;
		return new ValidationResult(true);
	}

	@Override
	public void process(String value) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			String json = mapper.writeValueAsString(params);
			OutputStream out = new FileOutputStream(exportFile);
			OutputStreamWriter writer = new OutputStreamWriter(out, "UTF-8");
			writer.write(json);
			writer.close();
			out.close();
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}

	@Override
	public Question next() {
		return nextQuestion;
	}

	@Override
	public boolean hasNext() {
		return true;
	}

}
