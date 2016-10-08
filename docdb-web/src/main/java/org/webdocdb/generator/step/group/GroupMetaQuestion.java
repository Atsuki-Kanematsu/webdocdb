package org.webdocdb.generator.step.group;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.webdocdb.core.document.DocumentContent;
import org.webdocdb.core.document.management.Group;
import org.webdocdb.core.util.StringUtil;
import org.webdocdb.generator.GenerationParameters;
import org.webdocdb.generator.step.Question;

@Component
public class GroupMetaQuestion implements Question {

	@Autowired
	private GenerationParameters params;
	
	@Autowired
	protected GroupAddtionalQuestion nextQuestion;
	
	@Override
	public ValidationResult validate(String value) {
		return new ValidationResult(true);
	}

	@Override
	public void process(String value) {
		if (!StringUtil.isEmpty(value)) {
			List<Group> groups = params.getGroups();
			Group lastGroup = groups.get(groups.size() - 1);
			lastGroup.setGroupMeta(new DocumentContent("application/json", value));
		}
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
		return "group-meta";
	}

}
