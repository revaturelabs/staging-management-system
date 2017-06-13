package com.revature.repositories;

import java.time.LocalDateTime;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.entities.Interview;

public interface InterviewsRepo extends JpaRepository<Interview, Long> {

	Set<Interview> findByAssociateId(long id);

	Set<Interview> findByClientId(long id);

	Set<Interview> findByInterviewStatusId(long id);

	Set<Interview> findByScheduledBetween(LocalDateTime now, LocalDateTime plusDays);

}
