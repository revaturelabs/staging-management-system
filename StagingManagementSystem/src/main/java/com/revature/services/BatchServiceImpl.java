package com.revature.services;

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
	public void add(Batch batch) {
		batchRepo.saveAndFlush(batch);
	}

}
