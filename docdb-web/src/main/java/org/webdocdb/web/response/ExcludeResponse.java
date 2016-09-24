package org.webdocdb.web.response;


public class ExcludeResponse extends Response {

	public ExcludeResponse(String message) {
		super.result = new ResponseResult(Response.RESULT_STATUS_EXCLUDED, message);
	}
}
