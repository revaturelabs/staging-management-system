package com.revature.sms.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.sms.entities.BatchType;

public interface BatchTypeRepo extends JpaRepository<BatchType, Long> {
	BatchType getByValue(String value);
}
