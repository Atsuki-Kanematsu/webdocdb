package org.webdocdb.generator.step.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.webdocdb.core.document.management.Account;
import org.webdocdb.core.util.StringUtil;
import org.webdocdb.generator.GenerationParameters;
import org.webdocdb.generator.step.Question;

@Component
public class AccountNameQuestion implements Question {

	@Autowired
	private GenerationParameters params;
	
	@Autowired
	private AccountLoginIdQuestion nextQuestion;
	
	
	@Override
	public ValidationResult validate(String value) {
		if (StringUtil.isEmpty(value)) {
			return new ValidationResult(false, getText() + "を指定してください");
		}
		for (Account account: params.getAccounts()) {
			if (account.getAccountName().equals(value)) {
				return new ValidationResult(false, "既に同じ名前で作成されています");
			}
		}
		return new ValidationResult(true);
	}

	@Override
	public void process(String value) {
		if (StringUtil.isEmpty(value)) {
			return;
		}
		Account account = new Account();
		account.setAccountName(value);
		params.getAccounts().add(account);
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
		return "account-name";
	}

}
