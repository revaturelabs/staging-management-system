package com.revature.services;

import com.revature.entities.Associate;
import com.revature.entities.StaggingAssociate;

import java.util.Set;

public interface AssociateService {

    public Associate getById(long id);
    public void add(Associate associate);
    public void delete(Associate associate);
    public void update(Associate associate);
    public Set<Associate> getAll();
    public Set<Associate> getAllActive();
    public Set<Associate> haveNoBatch();
    public Set<Associate> findByBatchId(Long id);
    public Set<StaggingAssociate> getAssociatesInStaggingOn(String date);
}
