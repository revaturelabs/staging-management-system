package com.revature.repositories;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.entities.Associate;
import com.revature.entities.Job;

public interface JobRepo extends JpaRepository<Job, Long>{
  Set<Job> getByAssociate(Associate associate);
}
