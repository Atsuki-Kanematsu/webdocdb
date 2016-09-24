package org.webdocdb.generator.step.instance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.webdocdb.core.document.DocumentContent;
import org.webdocdb.core.document.system.Instance;
import org.webdocdb.core.util.StringUtil;
import org.webdocdb.generator.GenerationParameters;
import org.webdocdb.generator.step.Question;
import org.webdocdb.generator.step.group.GroupAddtionalQuestion;

@Component
public class InstanceMetaQuestion implements Question {

	@Autowired
	private GenerationParameters params;
	
	@Autowired
	protected GroupAddtionalQuestion nextQuestion;
	
	@Override
	public ValidationResult validate(String value) {
		return new ValidationResult(true);
	}

	@Override
	public void process(String value) {
		if (!StringUtil.isEmpty(value)) {
			Instance instance = params.getInstance();
			instance.setInstanceMeta(new DocumentContent("application/json", value));
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

	@Override
	public String getText() {
		return "instance-meta";
	}

}
