package org.webdocdb.core.document.system;

import org.webdocdb.core.document.SystemDocument;
import org.webdocdb.core.document.annotation.PrimaryKey;

public class Language extends SystemDocument {

	@PrimaryKey
	private String languageId;
	private String languageName;
	
	public String getLanguageId() {
		return languageId;
	}
	public void setLanguageId(String languageId) {
		this.languageId = languageId;
	}
	public String getLanguageName() {
		return languageName;
	}
	public void setLanguageName(String languageName) {
		this.languageName = languageName;
	}

	
}
