package org.webdocdb.web.bean;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.webdocdb.core.document.system.Instance;

@Component
@Scope(value = WebApplicationContext.SCOPE_REQUEST)
public class RequestInstance extends Instance {

	@PostConstruct
	public void initialize() {
		
	}
}
