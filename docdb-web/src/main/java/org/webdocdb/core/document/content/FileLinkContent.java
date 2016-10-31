package org.webdocdb.core.document.content;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class FileLinkContent extends DocumentContent {

	private static final String VIRTUAL_NAME_KEY = "virtualName";
	private static final String SOURCE_FILEPATH_KEY = "sourceFilePath";
	
	public FileLinkContent() {
		initialize();
	}
	
	public FileLinkContent(String virtualName, File srcFile) throws IOException {
		if (!srcFile.exists()) {
			throw new FileNotFoundException(String.format("file is not exists.[%s]", srcFile.getAbsolutePath()));
		}
		initialize();
		this.setVirtualName(virtualName);
		this.setSourceFile(srcFile);
	}
	
	protected void initialize() {
		Map<String, String> map = new HashMap<String, String>();
		map.put(VIRTUAL_NAME_KEY, null);
		map.put(SOURCE_FILEPATH_KEY, null);
		DBObject content = new BasicDBObject(map);
		super.setContent(content);
	}

	public String getVirtualName() {
		DBObject dbObj = super.getContent();
		return dbObj.get(VIRTUAL_NAME_KEY).toString();
	}

	public void setVirtualName(String virtualName) {
		DBObject content = super.getContent();
		content.put(VIRTUAL_NAME_KEY, virtualName);
	}
	
	public File getSourceFile() {
		DBObject dbObj = super.getContent();
		Object filepath = dbObj.get(SOURCE_FILEPATH_KEY);
		if (filepath == null) {
			return null;
		}
		return new File(filepath.toString());
	}
	
	public void setSourceFile(File fileLink) {
		DBObject content = super.getContent();
		content.put(SOURCE_FILEPATH_KEY, fileLink.getAbsolutePath());
	}
	
}
