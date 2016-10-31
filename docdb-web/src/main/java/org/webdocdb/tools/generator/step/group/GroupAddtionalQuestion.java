package org.webdocdb.tools.generator.step.group;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.webdocdb.core.document.management.Group;
import org.webdocdb.core.util.ArrayUtil;
import org.webdocdb.core.util.StringUtil;
import org.webdocdb.tools.generator.GenerationParameters;
import org.webdocdb.tools.generator.step.Question;
import org.webdocdb.tools.generator.step.account.AccountAddtionalQuestion;
import org.webdocdb.tools.generator.step.account.AdminAccountPasswordQuestion;

@Component
public class GroupAddtionalQuestion implements Question {

	protected static final String[] ALLOW_VALUES = {"Y", "N"};
	protected static final String DEFAULT_VALUE = "N";
	
	@Autowired
	private GenerationParameters params;
	
	@Autowired
	protected AdminAccountPasswordQuestion skippedQuestion;

	@Autowired
	protected GroupNameQuestion nextQuestion;

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
		params.getGroups().add(createGroup("instanceOwners", Group.GROUP_TYPE_SYSTEM));
		params.getGroups().add(createGroup("administrators", Group.GROUP_TYPE_SYSTEM));
		params.getGroups().add(createGroup("developers", Group.GROUP_TYPE_SYSTEM));
		params.getGroups().add(createGroup("maintenancer", Group.GROUP_TYPE_SYSTEM));
		params.getGroups().add(createGroup("securityManagers", Group.GROUP_TYPE_SYSTEM));
		params.getGroups().add(createGroup("users", Group.GROUP_TYPE_SYSTEM));
		
		if (StringUtil.isEmpty(value)) {
			value = DEFAULT_VALUE;
		}
		if (value.toUpperCase().equals("N")) {
			isAddtionalSkip = true;
		}
	}
	
	protected Group createGroup(String name, int groupType) {
		Group group = new Group();
		group.setGroupName(name);
		group.setGroupType(groupType);
		return group;
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
