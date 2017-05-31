package com.revature.repositories;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.entities.Interviews;

public interface InterviewsRepo extends JpaRepository<Interviews, Long> {
	Set<Interviews> findById(long id);
}
