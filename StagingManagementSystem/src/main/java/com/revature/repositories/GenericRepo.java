package com.revature.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.entities.Location;

public interface GenericRepo extends JpaRepository<Location, Long>{
	Location findById(long id);
}
