package com.revature.sms.repositories;

import java.util.List;

import com.revature.sms.entities.Associate;
import com.revature.sms.entities.Batch;
import com.revature.sms.security.models.SalesforceUser;

public interface SalesforceRepo {
	public List<Batch> getRelevantBatches(SalesforceUser user);
	public List<Associate> getBatchTrainees(String resourceId, SalesforceUser user);
}
