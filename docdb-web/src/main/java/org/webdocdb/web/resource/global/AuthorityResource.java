package org.webdocdb.web.resource.global;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.webdocdb.web.logic.authority.TestLogic;
import org.webdocdb.web.logic.authority.TouchLogic;
import org.webdocdb.web.response.Response;

@Component
@Scope("request")
@Path("/{instanceName}/auth")
public class AuthorityResource {

	@PathParam("instanceName")
	protected String instanceName;
	
	@Autowired
	protected TouchLogic touchLogic;
	
	@Autowired
	protected TestLogic testLogic;
	
	@Path("/touch")
	@GET
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public Response touch(HttpServletRequest req) {
		return touchLogic.doLogic();
	}
	
	@Path("/test")
	@GET
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public Response test(HttpServletRequest req) {
		return testLogic.doLogic();
	}
	
}
