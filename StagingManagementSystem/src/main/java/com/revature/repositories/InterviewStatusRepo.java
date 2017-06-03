package com.revature.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.entities.InterviewStatuses;

public interface InterviewStatusRepo extends JpaRepository<InterviewStatuses, Long> {
	
	 InterviewStatuses findByInterviewsId(long id);
	
	 InterviewStatuses findByStatus(String status);
}
