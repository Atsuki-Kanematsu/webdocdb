package org.webdocdb.generator.step.instance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.webdocdb.core.document.system.Instance;
import org.webdocdb.core.service.system.InstanceService;
import org.webdocdb.core.util.StringUtil;
import org.webdocdb.generator.GenerationParameters;
import org.webdocdb.generator.step.Question;

@Component
public class InstanceNameQuestion implements Question {

	@Autowired
	protected InstanceService service;
	
	@Autowired
	private GenerationParameters params;
	
	@Autowired
	protected InstanceMetaQuestion nextQuestion;
	
	@Override
	public ValidationResult validate(String value) {
		if (StringUtil.isEmpty(value)) {
			return new ValidationResult(false, getText() + "を指定してください");
		}
		Instance instance = service.findByName(value);
		if (instance != null) {
			return new ValidationResult(false, "既に同じ名前で作成されています");
		}
		return new ValidationResult(true);
	}

	@Override
	public void process(String value) {
		params.getInstance().setInstanceName(value);
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
		return "instance-name";
	}

}
