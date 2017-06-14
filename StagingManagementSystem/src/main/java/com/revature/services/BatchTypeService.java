package com.revature.services;

import com.revature.entities.BatchType;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface BatchTypeService {

    public BatchType add(BatchType batchType);
    public void delete(BatchType batchType);
    public void update(BatchType batchType);
    public BatchType findById(long id);
    public Set<BatchType> getAll();
}
