package com.revature.sms.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.sms.entities.AssociatesStatus;

@Repository
public interface AssociatesStatusRepo extends JpaRepository<AssociatesStatus,Integer>{
	AssociatesStatus getByStatus(String status);
}
