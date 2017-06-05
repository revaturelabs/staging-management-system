package com.revature.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.entities.Job;

public interface JobRepo extends JpaRepository<Job, Long>{
}
