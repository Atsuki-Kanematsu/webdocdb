package org.webdocdb.web.logic.authority;

import javax.ws.rs.PathParam;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.webdocdb.web.logic.SimpleLogic;
import org.webdocdb.web.response.Response;

@Component
@Scope("request")
public class TouchLogic extends SimpleLogic {

	@PathParam("instanceName")
	protected String instanceName;
	
	@Override
	protected Response execute() {
		System.out.println(instanceName);
		return super.execute();
	}

	
}
