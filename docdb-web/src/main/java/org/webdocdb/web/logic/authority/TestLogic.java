package org.webdocdb.web.logic.authority;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.webdocdb.core.document.Document;
import org.webdocdb.core.document.system.Instance;
import org.webdocdb.core.service.system.InstanceService;
import org.webdocdb.web.logic.SimpleLogic;
import org.webdocdb.web.response.Response;

@Component
@Scope("request")
public class TestLogic extends SimpleLogic {

	@Autowired
	protected InstanceService instanceService;
	
	@Override
	protected Response execute() {
		Instance instance = new Instance();
		instance.setInstanceName("sample");
		instance.setStatus(Document.STATUS_ENABLE);
		instanceService.create(instance);
		return super.execute();
	}

	
}
