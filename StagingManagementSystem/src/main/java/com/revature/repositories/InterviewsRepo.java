package com.revature.repositories;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.entities.Interviews;

public interface InterviewsRepo extends JpaRepository<Interviews, Long> {
	
	 Interviews findByAssociateId(long id);
	
	 Interviews findByClientId(long id);
	 
	 Interviews findByInterviewStatusId(long id);
	
	 Interviews findByScheduled(LocalDateTime dateandtime);

}
