package com.revature.services;

import java.util.List;

import com.revature.entities.InterviewStatuses;

public interface InterviewStatusService {
	
	//C
	void add(InterviewStatuses interviewstatus);
	//R
	public InterviewStatuses findById(long id);
	public List<InterviewStatuses> getAll();
	public InterviewStatuses findByInterviewsId(long id);
	public InterviewStatuses findByStatus(String status);
	//U
	public void update(InterviewStatuses interviewstatus);
	//D
	public void delete(InterviewStatuses interviewstatus);

}
