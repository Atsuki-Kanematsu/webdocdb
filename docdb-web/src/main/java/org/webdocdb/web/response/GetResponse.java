package org.webdocdb.web.response;

import java.util.HashMap;
import java.util.Map;

public class GetResponse extends Response {

	public GetResponse(String updateToken, Object entity) {
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("updateToken", updateToken);
		data.put("content", entity);
		super.data = data;
		super.result = new ResponseResult(RESULT_STATUS_SUCCESS, "");
	}
}
