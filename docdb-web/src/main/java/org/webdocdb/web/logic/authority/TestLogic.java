package org.webdocdb.web.logic.authority;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.webdocdb.web.logic.SimpleLogic;
import org.webdocdb.web.response.Response;

@Component
@Scope("request")
public class TestLogic extends SimpleLogic {

	@Override
	protected Response execute() {
		
		return super.execute();
	}

	
}
