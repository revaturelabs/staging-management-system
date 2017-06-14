package com.revature.repositories;

import com.revature.entities.Associate;
import com.revature.entities.Job;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface JobRepo extends JpaRepository<Job, Long> {

    Set<Job> getByAssociate(Associate associate);
}
