package org.webdocdb.core.listener.initial;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.webdocdb.core.document.Document;
import org.webdocdb.core.document.annotation.IdGenerate;
import org.webdocdb.core.listener.DocumentRegisterListener;
import org.webdocdb.core.manager.IdManager;

@Service
@Scope("singleton")
public class IdGenerator implements DocumentRegisterListener {
	
	@Autowired
	protected IdManager idManager;

	@Override
	public void beforeInsert(Document document) {
		setGeneratedId(document);
	}

	@Override
	public void afterInsert(Document document) {
	}

	@Override
	public void beforeUpdate(Query query, Document before, Document after) {
		setGeneratedId(after);
	}

	@Override
	public void afterUpdate(Query query, Document after) {
	}

	@Override
	public void beforeDelete(Query query, Document document) {
	}

	@Override
	public void afterDelete(Query query, Document document) {
	}

	protected void setGeneratedId(Document document) {
		Field[] fields = document.getClass().getDeclaredFields();
		for (Field field : fields) {
			try {
				Annotation annotation = field.getDeclaredAnnotation(IdGenerate.class);
				if (annotation == null) {
					continue;
				}
				field.setAccessible(true);
				if (field.get(document) != null) {
					continue;
				}
				IdGenerate ig = (IdGenerate) annotation;
				int idType = ig.idType();
				boolean isReserve = ig.reserve();
				String id;
				if (isReserve) {
					id = idManager.generate(idType);
				} else {
					id = idManager.generateAndActivate(idType);
				}
				field.set(document, id);
			} catch (Exception ex) {
				ex.printStackTrace();
				continue;
			}
		}
	}
}
