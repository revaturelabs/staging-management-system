package com.revature.sms.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.sms.entities.InterviewStatuses;

public interface InterviewStatusRepo extends JpaRepository<InterviewStatuses, Long> {

    InterviewStatuses findByValue(String status);
}
