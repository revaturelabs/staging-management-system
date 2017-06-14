package com.revature.repositories;

import com.revature.entities.InterviewStatuses;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InterviewStatusRepo extends JpaRepository<InterviewStatuses, Long> {

    InterviewStatuses findByValue(String status);
}
