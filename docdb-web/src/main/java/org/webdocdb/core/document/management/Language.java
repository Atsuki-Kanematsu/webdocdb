package org.webdocdb.core.document.management;

import org.webdocdb.core.document.ManagementDocument;
import org.webdocdb.core.document.annotation.Index;
import org.webdocdb.core.document.annotation.IndexField;
import org.webdocdb.core.document.annotation.Indexes;
import org.webdocdb.core.document.annotation.PrimaryKey;
import org.webdocdb.core.document.annotation.IndexField.IndexOrder;

@Indexes({
	@Index(fields={@IndexField(name="languageId", order=IndexOrder.ASC)}, unique=true),
	@Index(fields={@IndexField(name="instanceId", order=IndexOrder.ASC), 
			@IndexField(name="languageKey", order=IndexOrder.ASC)})
})
public class Language extends ManagementDocument {

	@PrimaryKey
	private String languageId;
	private String languageKey;
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
	public String getLanguageKey() {
		return languageKey;
	}
	public void setLanguageKey(String languageKey) {
		this.languageKey = languageKey;
	}
	public void setLanguageName(String languageName) {
		this.languageName = languageName;
	}

	
}
