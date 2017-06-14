package com.revature.services;

import com.revature.entities.Associate;
import com.revature.entities.Job;

import java.util.Set;

public interface JobService {

    public void add(Job job);
    public void delete(Job job);
    public void update(Job job);
    public Job findById(long id);
    public Set<Job> findByAssociate(Associate associate);
    public Set<Job> getAll();
}
