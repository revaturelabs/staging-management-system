package com.revature.sms.salesforce;

import org.springframework.stereotype.Component;

import com.revature.salesforce.beans.BatchTrainer;
import com.revature.salesforce.beans.SalesforceBatch;
import com.revature.salesforce.beans.SalesforceTrainee;
import com.revature.sms.entities.Associate;
import com.revature.sms.entities.Batch;
import com.revature.sms.entities.Trainer;

@Component
public class SalesforceTransformerToSMS implements SalesforceTransformer{

	@Override
	public Batch transformBatch(SalesforceBatch salesforceBatch) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Trainer transformTrainer(BatchTrainer batchTrainer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Associate transformTrainee(SalesforceTrainee salesforceTrainee) {
		// TODO Auto-generated method stub
		return null;
	}

}
