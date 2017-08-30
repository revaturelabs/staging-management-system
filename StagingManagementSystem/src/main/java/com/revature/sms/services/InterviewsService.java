package com.revature.sms.services;

import java.util.Set;

import com.revature.sms.entities.Interview;

public interface InterviewsService {

    // C
    void add(Interview interviews);
    // R
    public Interview findById(long id);
    public Set<Interview> findByAssociateId(long id);
    public Set<Interview> findByClientId(long id);
    public Set<Interview> findByInterviewStatus(long id);
    public Set<Interview> getAll();
    // U
    public void update(Interview interviews);
    // D
    public void delete(Interview interviews);
    Set<Interview> nextFiveDays();
}
