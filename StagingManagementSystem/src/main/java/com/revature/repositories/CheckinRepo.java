package com.revature.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.entities.Check;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Set;

/**
 * Created by mnikitin on 6/1/17.
 */
public interface CheckinRepo extends JpaRepository<Check, Long> {
    Set<Check> getAllByCheckinTimeBetween(LocalDateTime startPoint, LocalDateTime endPoint);
}
