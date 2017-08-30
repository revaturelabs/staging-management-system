package com.revature.sms.services;

import java.util.Set;

import com.revature.sms.entities.InterviewQuestion;

public interface InterviewQuestionService {

    public void add(InterviewQuestion interviewQ);
    public void delete(InterviewQuestion interviewQ);
    public void update(InterviewQuestion interviewQ);
    public InterviewQuestion findById(long id);
    public Set<InterviewQuestion> getAll();
}
