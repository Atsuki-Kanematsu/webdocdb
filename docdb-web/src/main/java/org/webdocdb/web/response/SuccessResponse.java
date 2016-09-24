package org.webdocdb.web.response;

public class SuccessResponse extends Response {

	public SuccessResponse(Object data) {
		this(data, "");
	}

	public SuccessResponse(Object data, String message) {
		super.result = new ResponseResult(Response.RESULT_STATUS_SUCCESS, message);
		super.data = data;
	}

}
