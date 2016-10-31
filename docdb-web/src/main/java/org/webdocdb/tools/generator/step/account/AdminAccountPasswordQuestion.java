package org.webdocdb.tools.generator.step.account;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.webdocdb.core.document.management.Account;
import org.webdocdb.core.util.ArrayUtil;
import org.webdocdb.core.util.HashUtil;
import org.webdocdb.core.util.HashUtil.HashAlgorithm;
import org.webdocdb.tools.generator.GenerationParameters;
import org.webdocdb.tools.generator.step.Question;
import org.webdocdb.tools.generator.step.account.AdminAccountPasswordQuestion;
import org.webdocdb.tools.generator.step.last.SettingExportQuestion;
import org.webdocdb.core.util.StringUtil;

@Component
public class AdminAccountPasswordQuestion implements Question {

	protected static final String DEFAULT_VALUE = "P@ssw0rd";
	protected static final String PASSWORD_BASE_PATTERN = "^[a-zA-Z0-9!-~]+$";
	protected static final int PASSWORD_MIN_LENGTH = 8;
	protected static final String[] PASSWORD_PATTERNS = {
			".*[a-z].*",
			".*[A-Z].*",
			".*[0-9].*"
	};
	protected static final int PASSWORD_PATTERN_MIN_MATCHED = 2;
	
	@Autowired
	private GenerationParameters params;
	
	@Autowired
	protected AccountAddtionalQuestion nextQuestion;
	
	@Override
	public String getText() {
		return String.format("管理者アカウント(admin)のパスワード(default:%s)", DEFAULT_VALUE);
	}

	@Override
	public ValidationResult validate(String value) {
		if (StringUtil.isEmpty(value)) {
			value = DEFAULT_VALUE;
		}
		if (value.length() < PASSWORD_MIN_LENGTH) {
			return new ValidationResult(false, 
					"8桁以上で指定してください");
		}
		if (!value.matches(PASSWORD_BASE_PATTERN)) {
			return new ValidationResult(false, 
					"半角英数字、または記号のみ指定可能です");
		}
		int patternMatched = 0;
		for (String pattern : PASSWORD_PATTERNS) {
			if (value.matches(pattern)) {
				patternMatched++;
			}
		}
		if (patternMatched < PASSWORD_PATTERN_MIN_MATCHED) {
			return new ValidationResult(false, 
					String.format("大文字、小文字、数字のいずれか%dつが含まれている必要があります", PASSWORD_PATTERN_MIN_MATCHED));
		}
		return new ValidationResult(true);
	}

	@Override
	public void process(String value) {
		if (StringUtil.isEmpty(value)) {
			value = DEFAULT_VALUE;
		}
		params.getAccounts().add(createAccount("管理者アカウント", "admin", value, "administrators"));
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
		return nextQuestion;
	}

	@Override
	public boolean hasNext() {
		return true;
	}

}
