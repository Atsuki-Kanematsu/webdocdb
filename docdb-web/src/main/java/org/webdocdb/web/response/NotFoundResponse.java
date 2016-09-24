package org.webdocdb.web.response;


public class NotFoundResponse extends Response {

	public NotFoundResponse(String message) {
		super.result = new ResponseResult(Response.RESULT_STATUS_NOT_FOUND, message);
	}
}
