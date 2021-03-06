package com.revature.sms.services;

import java.util.Set;

import com.revature.sms.entities.Associate;
import com.revature.sms.entities.StaggingAssociate;

public interface AssociateService {

    public Associate getById(long id);
    public void add(Associate associate);
    public void delete(Associate associate);
    public void update(Associate associate);
    public void updateStatus(Associate associate);
    public Set<Associate> getAll();
    public Set<Associate> getAllActive();
    public Set<Associate> haveNoBatch();
    public Set<Associate> haveNoProject();
    public Set<Associate> findByBatchId(Long id);
    public Set<Associate> findByProjectId(Long id);
    public Set<StaggingAssociate> getAssociatesInStaggingOn(String date);
    public Set<Associate> findByNameLike(String name);
    public Set<Associate> getAllByStatus(String status);
    public Set<Associate> findByAssociateStatus(String status);
    public int countAssociateByAssociateStatus_Status(String status);
    public Set<Associate> findByLatestPanelStatus(String latestPanelStatus);
}
