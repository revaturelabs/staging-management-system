package com.revature.sms.salesforce;

import com.revature.salesforce.beans.BatchTrainer;
import com.revature.salesforce.beans.SalesforceBatch;
import com.revature.salesforce.beans.SalesforceTrainee;
import com.revature.sms.entities.Associate;
import com.revature.sms.entities.Batch;
import com.revature.sms.entities.Trainer;

public interface SalesforceTransformer {
	public Batch transformBatch(SalesforceBatch salesforceBatch);
	public Trainer transformTrainer(BatchTrainer batchTrainer);
	public Associate transformTrainee(SalesforceTrainee salesforceTrainee);
}
