package com.revature.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.entities.BatchType;

public interface BatchTypeRepo extends JpaRepository<BatchType, Long> {

}
