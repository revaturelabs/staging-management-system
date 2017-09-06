package com.revature.sms.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.sms.entities.Checkin;

import java.time.LocalDateTime;
import java.util.Set;

/**
 * Created by mnikitin on 6/1/17.
 */
public interface CheckinRepo extends JpaRepository<Checkin, Long> {

    Set<Checkin> getAllByCheckinTimeBetween(LocalDateTime startPoint, LocalDateTime minusDays);
    Set<Checkin> getAllByAssociate_Id(long id);
}
