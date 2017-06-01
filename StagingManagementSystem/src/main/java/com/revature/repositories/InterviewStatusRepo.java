package com.revature.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.entities.InterviewStatus;

public interface InterviewStatusRepo extends JpaRepository<InterviewStatus, Long> {
	InterviewStatus findById(long id);
	
	InterviewStatus findByInterviews();
	
	InterviewStatus findByStatus(String status);
}
