package com.revature.sms.services;

import com.revature.sms.entities.InterviewStatuses;
import com.revature.sms.repositories.InterviewStatusRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class InterviewStatusServiceImpl implements InterviewStatusService {

    @Autowired
    InterviewStatusRepo interviewStatusRepo;

    public InterviewStatusServiceImpl(InterviewStatusRepo interviewStatusRepo) {

        super();
        this.interviewStatusRepo = interviewStatusRepo;
    }

    @Override
    public void add(InterviewStatuses interviewStatus) {

        interviewStatusRepo.saveAndFlush(interviewStatus);
    }

    @Override
    public InterviewStatuses findById(long id) {

        return interviewStatusRepo.getOne(id);
    }

    @Override
    public Set<InterviewStatuses> getAll() {

        return new HashSet<InterviewStatuses>(interviewStatusRepo.findAll());
    }

    @Override
    public void update(InterviewStatuses interviewStatus) {

        interviewStatusRepo.saveAndFlush(interviewStatus);
    }

    @Override
    public void delete(InterviewStatuses interviewStatus) {

        interviewStatusRepo.delete(interviewStatus);
    }

    @Override
    public InterviewStatuses findByStatus(String status) {

        return interviewStatusRepo.findByValue(status);
    }
}
