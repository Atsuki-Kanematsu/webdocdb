package org.webdocdb.core.document.content;

import java.io.IOException;

import org.webdocdb.core.exception.DocumentContentParseException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;

public class JsonContent extends DocumentContent {

	public static final String CONTENT_TYPE = "application/json";
	
	public JsonContent() throws DocumentContentParseException {
		this("{}");
	}
	
	public JsonContent(String jsonString) throws DocumentContentParseException {
		ObjectMapper mapper = new ObjectMapper();
		try {
			mapper.readTree(jsonString);
		} catch (IOException e) {
			throw new DocumentContentParseException(e);
		}
		super.setContentType(CONTENT_TYPE);
		super.setContent((DBObject) JSON.parse(jsonString));
	}
	
	public void setJsonString(String jsonString) {
		super.setContent((DBObject) JSON.parse(jsonString));
	}
	
	public String getJsonString() {
		return super.getContent().toString();
	}
}
