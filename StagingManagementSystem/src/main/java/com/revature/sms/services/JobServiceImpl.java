package com.revature.sms.services;

import com.revature.sms.entities.Associate;
import com.revature.sms.entities.Job;
import com.revature.sms.repositories.JobRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class JobServiceImpl implements JobService {

    @Autowired
    JobRepo jobRepo;

    public JobServiceImpl(JobRepo jobRepo) {

        super();
        this.jobRepo = jobRepo;
    }

    @Override
    public void add(Job job) {

        jobRepo.saveAndFlush(job);
    }

    @Override
    public Set<Job> getAll() {

        return new HashSet<Job>(jobRepo.findAll());
    }

    @Override
    public Job findById(long id) {

        return jobRepo.getOne(id);
    }

    @Override
    public void delete(Job job) {

        jobRepo.delete(job);
    }

    @Override
    public void update(Job job) {

        jobRepo.saveAndFlush(job);
    }

    @Override
    public Set<Job> findByAssociate(Associate associate) {

        return jobRepo.getByAssociate(associate);
    }
}
