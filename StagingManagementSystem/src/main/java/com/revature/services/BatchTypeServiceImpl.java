package com.revature.services;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.entities.BatchType;
import com.revature.repositories.BatchTypeRepo;

@Service
public class BatchTypeServiceImpl implements BatchTypeService {

	@Autowired
	BatchTypeRepo batchTypeRepo;

	public BatchTypeServiceImpl(BatchTypeRepo batchTypeRepo) {
		super();
		this.batchTypeRepo = batchTypeRepo;
	}

	@Override
	public void add(BatchType batchType) {
		batchTypeRepo.saveAndFlush(batchType);
	}

	@Override
	public Set<BatchType> getAll() {
		return new HashSet<BatchType>(batchTypeRepo.findAll());
	}

	@Override
	public BatchType findById(long id) {
		return batchTypeRepo.getOne(id);
	}

	@Override
	public void delete(BatchType batchType) {
		batchTypeRepo.delete(batchType);
	}

	@Override
	public void update(BatchType batchType) {
		batchTypeRepo.saveAndFlush(batchType);
	}
}
