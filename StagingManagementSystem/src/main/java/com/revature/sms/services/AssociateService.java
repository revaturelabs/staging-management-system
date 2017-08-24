package com.revature.sms.services;

import java.util.Set;

import com.revature.sms.entities.Associate;
import com.revature.sms.entities.AssociatesStatus;
import com.revature.sms.entities.StaggingAssociate;

public interface AssociateService {

    public Associate getById(long id);
    public void add(Associate associate);
    public void delete(Associate associate);
    public void update(Associate associate);
    public Set<Associate> getAll();

    //we took this out
    public Set<Associate> getAllActive();

    public Set<Associate> haveNoBatch();
    public Set<Associate> haveNoProject();
    public Set<Associate> findByBatchId(Long id);
    public Set<StaggingAssociate> getAssociatesInStaggingOn(String date);

    //we added this
    public Set<Associate> findByAssociateStatus(String status);

    //added this is update a status
    public void updateStatus(Associate associate);
}
