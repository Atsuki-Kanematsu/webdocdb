package org.webdocdb.web.logic;

import org.webdocdb.web.response.Response;
import org.webdocdb.web.response.SuccessResponse;

public class SimpleLogic extends AbstractLogic implements ILogic {

	@Override
	protected Response execute() {
		return new SuccessResponse("ok");
	}

}
