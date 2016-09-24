package org.webdocdb.generator.step.account;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.webdocdb.core.document.system.Account;
import org.webdocdb.core.util.ArrayUtil;
import org.webdocdb.core.util.HashUtil;
import org.webdocdb.core.util.HashUtil.HashAlgorithm;
import org.webdocdb.core.util.StringUtil;
import org.webdocdb.generator.GenerationParameters;
import org.webdocdb.generator.step.Question;
import org.webdocdb.generator.step.account.AccountAddtionalQuestion;

@Component
public class AccountAddtionalQuestion implements Question {

	protected static final String[] ALLOW_VALUES = {"Y", "N"};
	protected static final String DEFAULT_VALUE = "N";
	
	@Autowired
	private GenerationParameters params;
	
	@Autowired
	protected AccountAddtionalQuestion skippedQuestion;

	@Autowired
	protected AccountNameQuestion nextQuestion;

	private boolean isAddtionalSkip = false;
	
	@Override
	public String getText() {
		return "セキュリティグループの追加[Y/N](default:N)";
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
		params.getAccounts().add(createAccount("admin", "admin", "admin", "administrators"));
		if (StringUtil.isEmpty(value)) {
			value = DEFAULT_VALUE;
		}
		if (value.toUpperCase().equals("N")) {
			isAddtionalSkip = true;
		}
	}
	
	protected Account createAccount(String name, String loginId, String password, String primaryGroup) {
		Account account = new Account();
		account.setAccountName(name);
		account.setLoginId(loginId);
		account.setPassword(password);
		account.setInitialPassword(HashUtil.hash(HashAlgorithm.SHA_256, password));
		account.setPassword(account.getInitialPassword());
		account.setPasswordUpdateDatetime(new Date());
		account.setEnableFrom(new Date());
		account.setPrimaryGroupId(primaryGroup);
		return account;
	}

	@Override
	public Question next() {
		if (isAddtionalSkip) {
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
