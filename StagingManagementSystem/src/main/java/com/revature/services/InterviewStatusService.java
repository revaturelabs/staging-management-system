package com.revature.services;

import java.util.Set;

import com.revature.entities.InterviewStatuses;

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
