package org.webdocdb.tools.generator.step.last;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.webdocdb.core.manager.InstanceManager;
import org.webdocdb.core.transaction.TransactionThreadManager;
import org.webdocdb.core.util.ArrayUtil;
import org.webdocdb.core.util.StringUtil;
import org.webdocdb.tools.generator.GenerationParameters;
import org.webdocdb.tools.generator.InstanceGenerator;
import org.webdocdb.tools.generator.step.Question;

@Component
public class GenerateQuestion implements Question {

	protected static final String[] ALLOW_VALUES = {"Y", "N"};
	
	@Autowired
	private GenerationParameters params;
	
	@Autowired
	private InstanceManager instanceManager;
	
	@Autowired
	private TransactionThreadManager transactionManager;
	
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
		// タイムスタンプの設定
		String accountId = InstanceGenerator.class.getSimpleName();
		accountId = StringUtil.toLowerCamel(accountId);
		transactionManager.in(accountId);
		instanceManager.create(params.getInstance());
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
