package com.revature.services;

import java.util.Set;

import org.springframework.stereotype.Service;

import com.revature.entities.BatchType;

@Service
public interface BatchTypeService {

	public BatchType add(BatchType batchType);

	public void delete(BatchType batchType);

	public void update(BatchType batchType);

	public BatchType findById(long id);

	public Set<BatchType> getAll();
}
