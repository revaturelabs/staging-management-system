package com.revature.services;

import com.revature.entities.InterviewQuestion;

import java.util.Set;

public interface InterviewQuestionService {

    public void add(InterviewQuestion interviewQ);
    public void delete(InterviewQuestion interviewQ);
    public void update(InterviewQuestion interviewQ);
    public InterviewQuestion findById(long id);
    public Set<InterviewQuestion> getAll();
}
