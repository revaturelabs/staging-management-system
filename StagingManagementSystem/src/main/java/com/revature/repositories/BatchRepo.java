package com.revature.repositories;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.entities.Batch;

public interface BatchRepo extends JpaRepository<Batch, Long> {
	Set<Batch> findByInstructor(String instructor);
}
