package com.revature.repositories;


import java.time.LocalDateTime;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.entities.Checkin;

/**
 * Created by mnikitin on 6/1/17.
 */
public interface CheckinRepo extends JpaRepository<Checkin, Long> {
    Set<Checkin> getAllByCheckinTimeBetween(LocalDateTime startPoint, LocalDateTime endPoint);
    Set<Checkin> getAllByAssociate_Id(long id);


}
