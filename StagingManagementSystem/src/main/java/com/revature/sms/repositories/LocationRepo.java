package com.revature.sms.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.sms.entities.Location;

public interface LocationRepo extends JpaRepository<Location, Long> {

    Location findByName(String name);
}
