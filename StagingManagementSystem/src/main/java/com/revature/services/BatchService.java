package com.revature.services;

import com.revature.entities.Associate;
import com.revature.entities.Batch;
import com.revature.entities.BatchType;

public interface BatchService {

	void addAssociateToBatch(Batch batch, Associate associate);
	void add(Batch batch);

}
