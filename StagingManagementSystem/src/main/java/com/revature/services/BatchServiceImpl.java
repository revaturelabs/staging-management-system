package com.revature.services;

import com.revature.entities.Associate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.entities.Batch;
import com.revature.repositories.BatchRepo;

@Service
public class BatchServiceImpl implements BatchService {

	@Autowired
	BatchRepo batchRepo;

	public BatchServiceImpl(BatchRepo batchRepo) {
		super();
		this.batchRepo = batchRepo;
	}

	@Override
	public void addAssociateToBatch(Batch batch, Associate associate){
		batch.getAssociates().add(associate);
		batchRepo.saveAndFlush(batch);
	}

	@Override
	public void add(Batch batch) {
		batchRepo.saveAndFlush(batch);
	}



}
