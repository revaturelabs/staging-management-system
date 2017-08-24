package com.revature.sms.repositories;

import java.util.List;

import com.revature.sms.entities.Associate;
import com.revature.sms.entities.Batch;

public interface SalesforceRepo {
	public List<Batch> getRelevantBatches();
	public List<Associate> getBatchTrainees(String resourceId);
}
