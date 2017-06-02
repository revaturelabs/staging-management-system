package com.revature.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.entities.Interviews;

public interface InterviewsRepo extends JpaRepository<Interviews, Long> {
	
	 Interviews findByAssociateId(long id);
	
	 //Interviews findByClientId();
	 
	 Interviews findByInterviewStatusId(long id);
	//
	// Interviews findByScheduled(LocalDateTime dateandtime);

}
