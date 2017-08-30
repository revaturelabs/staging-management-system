package com.revature.sms.services;

import java.util.List;
import java.util.Set;

import com.revature.sms.entities.Associate;
import com.revature.sms.entities.Batch;
import com.revature.sms.entities.BatchType;

public interface BatchService {

    void addAssociateToBatch(Batch batch, Associate associate);
    void add(Batch batch);
    void addBatchTypes(Set<BatchType> batchTypes);
    void addMockBatches(Set<Batch> batches);
    public void delete(Batch batch);
    public void update(Batch batch);
    public List<Batch> getAll();
    public Batch findById(long id);
}
