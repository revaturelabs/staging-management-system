package com.revature.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.revature.entities.BatchType;

@Service
public interface BatchTypeService {
	
	public void add(BatchType batchType);
	public void delete(BatchType batchType);
	
	public void update(BatchType batchType);
	
	public BatchType findById(long id);
	public List<BatchType> getAll();
}
