package com.revature.sms.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.sms.entities.Associate;
import com.revature.sms.entities.Job;

import java.util.Set;

public interface JobRepo extends JpaRepository<Job, Long> {

    Set<Job> getByAssociate(Associate associate);
}
