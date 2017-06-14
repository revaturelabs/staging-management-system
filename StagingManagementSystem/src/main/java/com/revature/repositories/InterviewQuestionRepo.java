package com.revature.repositories;

import com.revature.entities.InterviewQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InterviewQuestionRepo extends JpaRepository<InterviewQuestion, Long> {

    InterviewQuestion findById(long id);
}
