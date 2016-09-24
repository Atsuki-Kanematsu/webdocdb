package org.webdocdb.generator.step.last;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.webdocdb.core.service.system.InstanceService;
import org.webdocdb.core.util.ArrayUtil;
import org.webdocdb.core.util.StringUtil;
import org.webdocdb.generator.GenerationParameters;
import org.webdocdb.generator.step.Question;

@Component
public class GenerateQuestion implements Question {

	protected static final String[] ALLOW_VALUES = {"Y", "N"};
	
	@Autowired
	private GenerationParameters params;
	
	@Autowired
	private InstanceService InstanceService;
	
	@Override
	public String getText() {
		return "生成の開始[Y/N]";
	}

	@Override
	public ValidationResult validate(String value) {
		if (StringUtil.isEmpty(value)) {
			return new ValidationResult(false, "生成の開始[Y/N]を指定してください");
		}
		if (!ArrayUtil.contains(ALLOW_VALUES, value.toUpperCase())) {
			return new ValidationResult(false, "\"Y\"か\"N\"で指定してください");
		}
		return new ValidationResult(true);
	}

	@Override
	public void process(String value) {
		InstanceService.create(params.getInstance());
	}

	@Override
	public Question next() {
		return null;
	}

	@Override
	public boolean hasNext() {
		return false;
	}

}
