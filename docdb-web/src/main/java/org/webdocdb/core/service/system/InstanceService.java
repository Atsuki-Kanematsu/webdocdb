package org.webdocdb.core.service.system;

import java.util.List;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.webdocdb.core.document.system.Collection;
import org.webdocdb.core.document.system.Instance;
import org.webdocdb.core.service.SystemDocumentService;

@Service
public class InstanceService extends SystemDocumentService<Instance> {

	public void create(Instance instance) {
		super.insert(instance, Collection.SYSTEM_COLLECTION);
	}
	
	public Instance findById(String instanceId) {
		return super.findById(instanceId);
	}
	
	public Instance findByName(String instanceName) {
		Query query = new Query(Criteria.where("instanceName").is(instanceName));
		List<Instance> list = super.find(query);
		if (list.size() == 0) {
			return null;
		}
		return list.get(0);
	}
	
}
