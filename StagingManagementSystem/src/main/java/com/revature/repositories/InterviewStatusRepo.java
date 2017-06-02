package com.revature.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.entities.InterviewStatuses;

public interface InterviewStatusRepo extends JpaRepository<InterviewStatuses, Long> {
	// InterviewStatus findById(long id);
	//
	// InterviewStatus findByInterviews();
	//
	// InterviewStatus findByStatus(String status);
}
