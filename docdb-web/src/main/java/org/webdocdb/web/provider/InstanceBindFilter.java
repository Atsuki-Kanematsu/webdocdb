package org.webdocdb.web.provider;

import java.io.IOException;

import javax.annotation.Priority;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.ext.Provider;

@Provider
@Priority(9000)
public class InstanceBindFilter implements ContainerRequestFilter {

	@Override
	public void filter(ContainerRequestContext context) throws IOException {
		
	}

}
