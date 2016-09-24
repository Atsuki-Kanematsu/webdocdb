package org.webdocdb.core.service.system;

import java.util.UUID;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.webdocdb.core.document.system.Collection;
import org.webdocdb.core.document.system.UniqueId;
import org.webdocdb.core.service.SystemDocumentService;

@Service
@Scope("singleton")
public class IdService extends SystemDocumentService<UniqueId> {

	public synchronized String generateId(int idType) {
		return createUniqueId(idType);
	}

	protected String createUniqueId(int idType) {
		String uuid = UUID.randomUUID().toString();
		UniqueId id = super.findById(uuid);
		if (id != null) {
			return createUniqueId(idType);
		}
		id = new UniqueId();
		id.setUniqueId(uuid);
		id.setIdType(idType);
		id.setInstanceId("");
		super.insert(id, Collection.SYSTEM_COLLECTION);
		return uuid;
	}
}
