package com.revature.services;

import java.util.List;

import com.revature.entities.InterviewStatus;

public interface InterviewStatusService {
	
	//C
	void add(InterviewStatus interviewstatus);
	//R
	public InterviewStatus findById(long id);
	public List<InterviewStatus> getAll();
	public InterviewStatus findByInterviews();
	public InterviewStatus findByStatus(String status);
	//U
	public void update(InterviewStatus interviewstatus);
	//D
	public void delete(InterviewStatus interviewstatus);

}
