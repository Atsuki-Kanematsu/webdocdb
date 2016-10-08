package org.webdocdb.core.builder;

import java.util.ArrayList;
import java.util.List;

import org.webdocdb.core.document.Document;
import org.webdocdb.core.document.annotation.Index;
import org.webdocdb.core.document.annotation.Indexes;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

import org.webdocdb.core.document.annotation.IndexField;

public class IndexQueryBuilder {

	public static <D extends Document> List<IndexQueryBuilder> parseDocumentClass(Class<D> documentClass) {
		List<IndexQueryBuilder> list = new ArrayList<>();
		Index index = documentClass.getDeclaredAnnotation(Index.class);
		if (index != null) {
			list.add(parseIndex(index));
		}
		Indexes indexes = documentClass.getDeclaredAnnotation(Indexes.class);
		for (Index idx : indexes.value()) {
			list.add(parseIndex(idx));
		}
		return list;
	}
	
	protected static <D extends Document> IndexQueryBuilder parseIndex(Index index) {
		IndexQueryBuilder builder = new IndexQueryBuilder();
		for (IndexField field : index.fields()) {
			builder.add(field.name(), field.order());
		}
		builder.setUnique(index.unique());
		return builder;
	}
	
	
	
	private List<IndexFieldDef> fieldDefs = new ArrayList<>();
	private boolean isUnique = false;
	
	public IndexQueryBuilder add(String fieldName) {
		return this.add(fieldName, IndexField.IndexOrder.ASC);
	}

	public IndexQueryBuilder add(String fieldName, IndexField.IndexOrder order) {
		fieldDefs.add(new IndexFieldDef(fieldName, order));
		return this;
	}
	
	public IndexQueryBuilder setUnique(boolean isUnique) {
		this.isUnique = isUnique;
		return this;
	}
	public DBObject getKeys() {
		DBObject option = new BasicDBObject();
		for (IndexFieldDef def : fieldDefs) {
			option.put(def.getFieldName(), def.order.intValue());
		}
		return option;
	}
	
	public DBObject getOption() {
		DBObject option = new BasicDBObject();
		option.put("unique", new Boolean(isUnique));
		return option;
	}
	
	public String toQueryString() {
		StringBuffer query = new StringBuffer();
		query.append("{");
		for (IndexFieldDef def : fieldDefs) {
			query.append(def.getFieldName());
			query.append(",");
			query.append(def.getOrder().intValue());
			query.append(",");
		}
		query.setLength(query.length() - 1);
		if (query.length() == 0) {
			//TODO:NoIndexDefs;
			throw new RuntimeException("");
		}
		query.append("}");
		if (isUnique) {
			query.append(",{unique:true}");
		}
		
		return query.toString();
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
