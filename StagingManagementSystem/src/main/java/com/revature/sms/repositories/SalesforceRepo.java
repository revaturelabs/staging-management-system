package com.revature.sms.repositories;

import java.util.List;
import java.util.Set;

import com.revature.sms.entities.Associate;
import com.revature.sms.entities.Batch;
import com.revature.sms.security.models.SalesforceUser;

public interface SalesforceRepo {
	
	public List<Batch> getRelevantBatches(SalesforceUser user);
	public List<Associate> getBatchTrainees(String resourceId, SalesforceUser user);
	public List<Associate> getSpecificAssociates(Set<Associate> associates, SalesforceUser user);
	List<Associate> getBenchTrainees(SalesforceUser user);
	Batch getBatch(String resourceId, SalesforceUser user);
	
}
