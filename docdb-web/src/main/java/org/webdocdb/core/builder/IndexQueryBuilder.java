package org.webdocdb.core.builder;

import java.util.ArrayList;
import java.util.List;

import org.webdocdb.core.document.Document;
import org.webdocdb.core.document.annotation.Index;
import org.webdocdb.core.document.annotation.Indexes;
import org.webdocdb.core.document.annotation.IndexField;

public class IndexQueryBuilder {

	public static <D extends Document> List<String> parseDocumentClass(Class<D> documentClass) {
		List<String> list = new ArrayList<>();
		Index index = documentClass.getDeclaredAnnotation(Index.class);
		if (index != null) {
			list.add(toQueryString(index));
		}
		Indexes indexes = documentClass.getDeclaredAnnotation(Indexes.class);
		for (Index idx : indexes.value()) {
			list.add(toQueryString(idx));
		}
		return list;
	}
	
	protected static String toQueryString(Index index) {
		IndexQueryBuilder builder = new IndexQueryBuilder();
		for (IndexField field : index.fields()) {
			builder = builder.add(field.name(), field.order());
		}
		return builder.toQueryString();
	}
	
	
	public IndexQueryBuilder add(String fieldName) {
		return this.add(fieldName, IndexField.IndexOrder.ASC);
	}

	public IndexQueryBuilder add(String fieldName, IndexField.IndexOrder order) {
		return this;
	}
	public String toQueryString() {
		return null;
	}
	
	public static class IndexFieldDef {
		
		private String fieldName;
		private IndexField.IndexOrder order;
		public IndexFieldDef(String fieldName, IndexField.IndexOrder order) {
			this.fieldName = fieldName;
			this.order = order;
		}
		public String getFieldName() {
			return fieldName;
		}
		public void setFieldName(String fieldName) {
			this.fieldName = fieldName;
		}
		public IndexField.IndexOrder getOrder() {
			return order;
		}
		public void setOrder(IndexField.IndexOrder order) {
			this.order = order;
		}
		
	}
	
}
