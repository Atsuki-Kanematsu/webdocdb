package org.webdocdb.web.response;

import java.util.Map;

public abstract class Response {

	public static final String RESULT_STATUS_SUCCESS = "success";
	public static final String RESULT_STATUS_NOT_FOUND = "notfound";
	public static final String RESULT_STATUS_INVALID = "invalid";
	public static final String RESULT_STATUS_EXCLUDED = "excluded";
	public static final String RESULT_STATUS_ERROR = "error";

	protected ResponseResult result;
	protected Map<String, String> invalidFields;
	protected Object data;

	public ResponseResult getResult() {
		return result;
	}
	public Map<String, String> getInvalidFields() {
		return invalidFields;
	}
	public Object getData() {
		return data;
	}

	public static class ResponseResult {
		private String status;
		private String message;

		public ResponseResult(String status, String message) {
			this.status = status;
			this.message = message;
		}

		public String getStatus() {
			return status;
		}
		public String getMessage() {
			return message;
		}
	}
}
