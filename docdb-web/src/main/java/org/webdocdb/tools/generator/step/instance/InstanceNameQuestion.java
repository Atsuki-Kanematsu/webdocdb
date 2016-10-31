package org.webdocdb.tools.generator.step.instance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.webdocdb.core.manager.InstanceManager;
import org.webdocdb.core.util.StringUtil;
import org.webdocdb.tools.generator.GenerationParameters;
import org.webdocdb.tools.generator.step.Question;

@Component
public class InstanceNameQuestion implements Question {

	@Autowired
	protected InstanceManager instanceManager;
	
	@Autowired
	private GenerationParameters params;
	
	@Autowired
	protected InstanceMetaQuestion nextQuestion;
	
	@Override
	public ValidationResult validate(String value) {
		if (StringUtil.isEmpty(value)) {
			return new ValidationResult(false, getText() + "を指定してください");
		}
		if (instanceManager.exists(value)) {
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
		return "インスタンス名";
	}

}
