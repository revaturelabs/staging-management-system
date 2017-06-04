package com.revature.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.entities.Location;

public interface LocationRepo extends JpaRepository<Location, Long>{
}
