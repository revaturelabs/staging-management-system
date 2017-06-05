package com.revature.services;

import java.util.List;

import com.revature.entities.InterviewQuestion;

public interface InterviewQuestionService {
	public void add(InterviewQuestion interviewQ);
	public void delete(InterviewQuestion interviewQ);
	
	public void update(InterviewQuestion interviewQ);
	
	public InterviewQuestion findById(long id);
	public List<InterviewQuestion> getAll();
}
