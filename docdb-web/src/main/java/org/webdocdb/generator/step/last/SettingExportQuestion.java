package org.webdocdb.generator.step.last;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.webdocdb.core.util.ArrayUtil;
import org.webdocdb.core.util.StringUtil;
import org.webdocdb.generator.step.Question;

@Component
public class SettingExportQuestion implements Question {
	
	protected static final String[] ALLOW_VALUES = {"Y", "N"};
	protected static final String DEFAULT_VALUE = "N";
	
	@Autowired
	protected ExportPathQuestion nextQuestion;
	
	@Autowired
	protected GenerateQuestion skippedQuestion;
	
	private boolean isExportSkip = false;

	@Override
	public String getText() {
		return "設定内容のエクスポート[Y/N](default:N)";
	}

	@Override
	public ValidationResult validate(String value) {
		if (StringUtil.isEmpty(value)) {
			value = DEFAULT_VALUE;
		}
		if (!ArrayUtil.contains(ALLOW_VALUES, value.toUpperCase())) {
			return new ValidationResult(false, "\"Y\"か\"N\"で指定してください");
		}
		return new ValidationResult(true);
	}

	@Override
	public void process(String value) {
		if (StringUtil.isEmpty(value)) {
			value = DEFAULT_VALUE;
		}
		if (value.toUpperCase().equals("N")) {
			isExportSkip = true;
		}
	}

	@Override
	public Question next() {
		if (isExportSkip) {
			return skippedQuestion;
		} else {
			return nextQuestion;
		}
	}

	@Override
	public boolean hasNext() {
		return true;
	}

}
