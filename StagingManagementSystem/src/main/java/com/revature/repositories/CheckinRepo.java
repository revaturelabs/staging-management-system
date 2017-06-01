package com.revature.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.entities.Checkin;

public interface CheckinRepo extends JpaRepository<Checkin, Long>{

}
