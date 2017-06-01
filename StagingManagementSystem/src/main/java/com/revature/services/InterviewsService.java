package com.revature.services;

import java.time.LocalDateTime;
import java.util.List;

import com.revature.entities.Interviews;

public interface InterviewsService {
	
	//C
	void add(Interviews interviews);
	//R
	public Interviews findById(long id);
	public Interviews findByAssociate();
	public Interviews findByClient();
	public Interviews findByStatus();
	public Interviews findByScheduled(LocalDateTime dateandtime);
	public List<Interviews> getAll();
	//U
	public void update(Interviews interviews);
	//D
	public void delete(Interviews interviews);
	
}
