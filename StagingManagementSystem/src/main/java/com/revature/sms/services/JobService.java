package com.revature.sms.services;

import java.util.Set;

import com.revature.sms.entities.Associate;
import com.revature.sms.entities.Job;

public interface JobService {

    public void add(Job job);
    public void delete(Job job);
    public void update(Job job);
    public Job findById(long id);
    public Set<Job> findByAssociate(Associate associate);
    public Set<Job> getAll();
}
