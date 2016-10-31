package org.webdocdb.tools.generator.step.instance;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.webdocdb.core.document.content.JsonContent;
import org.webdocdb.core.document.system.Instance;
import org.webdocdb.core.exception.DocumentContentParseException;
import org.webdocdb.core.util.StringUtil;
import org.webdocdb.tools.generator.GenerationParameters;
import org.webdocdb.tools.generator.step.Question;
import org.webdocdb.tools.generator.step.group.GroupAddtionalQuestion;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class InstanceMetaQuestion implements Question {

	protected static final String DEFAULT_VALUE = "{}";
	
	@Autowired
	private GenerationParameters params;
	
	@Autowired
	protected GroupAddtionalQuestion nextQuestion;
	
	@Override
	public ValidationResult validate(String value) {
		if (StringUtil.isEmpty(value)) {
			return new ValidationResult(true);
		}
		ObjectMapper mapper = new ObjectMapper();
		try {
			mapper.readTree(value);
		} catch (IOException e) {
			return new ValidationResult(false, e.getMessage());
		}
		return new ValidationResult(true);
	}

	@Override
	public void process(String value) {
		if (StringUtil.isEmpty(value)) {
			value = DEFAULT_VALUE;
		}
		Instance instance = params.getInstance();
		JsonContent meta;
		try {
			meta = new JsonContent(value);
		} catch (DocumentContentParseException e) {
			throw new RuntimeException(e);
		}
		instance.setInstanceMeta(meta);
	}

	@Override
	public Question next() {
		return nextQuestion;
	}

	@Override
	public boolean hasNext() {
		return true;
	}

	@Override
	public String getText() {
		return "インスタンスメタ情報(JSON形式)[default:{}]";
	}

}
