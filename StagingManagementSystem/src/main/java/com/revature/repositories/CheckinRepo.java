package com.revature.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.entities.Checkin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Set;

/**
 * Created by mnikitin on 6/1/17.
 */
public interface CheckinRepo extends JpaRepository<Checkin, Long> {
    Set<Checkin> getAllByCheckinTimeBetween(LocalDateTime startPoint, LocalDateTime endPoint);
}
