package org.webdocdb.web.logic;

import org.apache.log4j.Logger;
import org.webdocdb.web.response.Response;

public abstract class AbstractLogic implements ILogic {

	protected static Logger logger = Logger.getLogger(AbstractLogic.class);
	
	protected void initialize() {
		logger.debug("logic started.");
	}
	
	protected abstract Response execute() throws Throwable;
	
	protected void terminate() {
		logger.debug("logic terminated.");
	}
	
	@Override
	public Response doLogic() {
		initialize();
		try {
			return execute();
		} catch (Throwable t) {
			logger.error("logic execution error.", t);
			t.printStackTrace();
			throw new RuntimeException(t);
		} finally {
			terminate();
		}
	}
	
}
