package org.webdocdb.tools.generator.step.group;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.webdocdb.core.document.management.Group;
import org.webdocdb.core.util.StringUtil;
import org.webdocdb.tools.generator.GenerationParameters;
import org.webdocdb.tools.generator.step.Question;

@Component
public class GroupNameQuestion implements Question {

	@Autowired
	private GenerationParameters params;
	
	@Autowired
	private GroupMetaQuestion nextQuestion;
	
	
	@Override
	public ValidationResult validate(String value) {
		if (StringUtil.isEmpty(value)) {
			return new ValidationResult(false, getText() + "を指定してください");
		}
		for (Group group : params.getGroups()) {
			if (group.getGroupName().equals(value)) {
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
		Group group = new Group();
		group.setGroupName(value);
		params.getGroups().add(group);
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
		return "group-name";
	}

}
