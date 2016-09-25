package org.webdocdb.generator.step.last;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.webdocdb.core.document.system.Account;
import org.webdocdb.core.service.system.InstanceService;
import org.webdocdb.core.transaction.TransactionThreadManager;
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
		// instanceIdの生成
		String instanceId = UUID.randomUUID().toString();
		
		// タイムスタンプの設定
		Account account = new Account();
		account.setAccountId("generator");
		account.setInstanceId(instanceId);
		transactionManager.in(account, new Date());
		
		// インスタンスの生成
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
