package org.webdocdb.web;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.spring.scope.RequestContextFilter;
import org.glassfish.jersey.servlet.ServletContainer;
import org.glassfish.jersey.servlet.ServletProperties;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.webdocdb.web.provider.InstanceBindFilter;

@Configuration
public class JerseyConfiguration extends ResourceConfig {

	public static class JerseyServletConfig extends ResourceConfig {
		public JerseyServletConfig() {
			register(JacksonFeature.class);
			register(RequestContextFilter.class);
			register(InstanceBindFilter.class);
			packages("org.webdocdb.web.resource");
		}
	}
	
	@Bean
	public ServletRegistrationBean jerseyServlet() {
		ServletRegistrationBean registration =
				new ServletRegistrationBean(new ServletContainer(), "/*");
		registration.addInitParameter(ServletProperties.JAXRS_APPLICATION_CLASS,
				JerseyServletConfig.class.getName());
		return registration;
	}
}
