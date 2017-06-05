package com.revature.services;

import java.time.LocalDateTime;
import java.util.Set;

import com.revature.entities.Interviews;

public interface InterviewsService {

	// C
	void add(Interviews interviews);

	// R
	public Interviews findById(long id);

	public Interviews findByAssociateId(long id);

	public Interviews findByClientId(long id);

	public Interviews findByInterviewStatus(long id);

	public Interviews findByScheduled(LocalDateTime dateandtime);

	public Set<Interviews> getAll();

	// U
	public void update(Interviews interviews);

	// D
	public void delete(Interviews interviews);

}
