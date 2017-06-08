package com.revature.repositories;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.entities.Interview;

public interface InterviewsRepo extends JpaRepository<Interview, Long> {
	
	 Interview findByAssociateId(long id);
	
	 Interview findByClientId(long id);
	 
	 Interview findByInterviewStatusId(long id);
	
	 Interview findByScheduled(LocalDateTime dateandtime);

}
