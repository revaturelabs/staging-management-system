package com.revature.repositories;

import com.revature.entities.Interview;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Set;

public interface InterviewsRepo extends JpaRepository<Interview, Long> {

    Set<Interview> findByAssociateId(long id);
    Set<Interview> findByClientId(long id);
    Set<Interview> findByInterviewStatusId(long id);
    Set<Interview> findByScheduledBetween(LocalDateTime now, LocalDateTime plusDays);
}
