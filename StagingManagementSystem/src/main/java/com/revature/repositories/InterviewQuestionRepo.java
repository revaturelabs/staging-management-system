package com.revature.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.entities.InterviewQuestion;

public interface InterviewQuestionRepo extends JpaRepository<InterviewQuestion, Long> {
	InterviewQuestion findById(long id);
}
