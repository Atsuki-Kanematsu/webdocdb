package org.webdocdb.core.document.content;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.activation.FileTypeMap;

public class FileLinkContent extends DocumentContent<File> {

	private String virtualName;
	
	public FileLinkContent(String virtualName, File srcFile) throws IOException {
		if (!srcFile.exists()) {
			throw new FileNotFoundException(String.format("file is not exists.[%s]", srcFile.getAbsolutePath()));
		}
		FileTypeMap fileTypeMap = FileTypeMap.getDefaultFileTypeMap();
		this.virtualName = virtualName;
		super.setContentType(fileTypeMap.getContentType(srcFile));
		super.setContent(srcFile);
	}

	public String getVirtualName() {
		return virtualName;
	}

	public void setVirtualName(String virtualName) {
		this.virtualName = virtualName;
	}
	
	
}
