package com.revature.repositories;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.entities.Interviews;

public interface InterviewsRepo extends JpaRepository<Interviews, Long> {
	Interviews findById(long id);
	
	Interviews findByAssociate();
	
	Interviews findByClient();
	
	Interviews findByStatus();
	
	Interviews findByScheduled(LocalDateTime dateandtime);
	
}
