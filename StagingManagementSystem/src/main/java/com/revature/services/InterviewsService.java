package com.revature.services;

import java.time.LocalDateTime;
import java.util.Set;

import com.revature.entities.Interview;

public interface InterviewsService {

	// C
	void add(Interview interviews);

	// R
	public Interview findById(long id);

	public Interview findByAssociateId(long id);

	public Interview findByClientId(long id);

	public Interview findByInterviewStatus(long id);

	public Interview findByScheduled(LocalDateTime dateandtime);

	public Set<Interview> getAll();

	// U
	public void update(Interview interviews);

	// D
	public void delete(Interview interviews);

}
