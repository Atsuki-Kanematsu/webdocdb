package org.webdocdb.web.response;

import java.util.HashMap;
import java.util.Map;


public class InvalidResponse extends Response {

	public InvalidResponse(String message) {
		this(message, new HashMap<String, String>());
	}

	public InvalidResponse(String message, Map<String, String> invalidFields) {
		super.result = new ResponseResult(Response.RESULT_STATUS_INVALID, message);
		super.invalidFields = invalidFields;
	}

	public InvalidResponse(String message, String field, String fieldMessage) {
		super.result = new ResponseResult(Response.RESULT_STATUS_INVALID, message);
		invalidFields = new HashMap<String, String>();
		invalidFields.put(field, fieldMessage);
	}
}
