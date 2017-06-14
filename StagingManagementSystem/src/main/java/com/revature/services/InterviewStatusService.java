package com.revature.services;

import com.revature.entities.InterviewStatuses;

import java.util.Set;

public interface InterviewStatusService {

    // C
    void add(InterviewStatuses interviewstatus);
    // R
    public InterviewStatuses findById(long id);
    public Set<InterviewStatuses> getAll();
    public InterviewStatuses findByStatus(String status);
    // U
    public void update(InterviewStatuses interviewstatus);
    // D
    public void delete(InterviewStatuses interviewstatus);
}
