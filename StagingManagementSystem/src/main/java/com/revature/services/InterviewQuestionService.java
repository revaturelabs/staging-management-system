package com.revature.services;

import java.util.Set;

import com.revature.entities.InterviewQuestion;

public interface InterviewQuestionService {
	public void add(InterviewQuestion interviewQ);

	public void delete(InterviewQuestion interviewQ);

	public void update(InterviewQuestion interviewQ);

	public InterviewQuestion findById(long id);

	public Set<InterviewQuestion> getAll();
}
