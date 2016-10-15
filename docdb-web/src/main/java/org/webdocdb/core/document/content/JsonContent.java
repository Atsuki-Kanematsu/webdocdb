package org.webdocdb.core.document.content;

import java.io.IOException;

import org.webdocdb.core.exception.DocumentContentParseException;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonContent extends DocumentContent<JsonNode> {

	public static final String CONTENT_TYPE = "application/json";
	
	public JsonContent() throws DocumentContentParseException {
		this("{}");
	}
	
	public JsonContent(String jsonString) throws DocumentContentParseException {
		ObjectMapper mapper = new ObjectMapper();
		JsonNode content;
		try {
			content = mapper.readTree(jsonString);
		} catch (IOException e) {
			throw new DocumentContentParseException(e);
		}
		super.setContentType(CONTENT_TYPE);
		super.setContent(content);
	}
}
