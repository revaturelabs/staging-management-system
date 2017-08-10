package com.revature.sms.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.sms.entities.InterviewQuestion;

public interface InterviewQuestionRepo extends JpaRepository<InterviewQuestion, Long> {

    InterviewQuestion findById(long id);
}
