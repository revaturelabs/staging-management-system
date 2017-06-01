package com.revature.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.entities.Checkin;
import com.revature.repositories.CheckinRepo;
import com.revature.entities.Associate;
import com.revature.entities.Checkin;
import com.revature.entities.Manager;
import com.revature.exceptions.AlreadyCheckedInException;
import com.revature.exceptions.AlreadyCheckedOutException;
import com.revature.exceptions.NotCheckedInException;
import com.revature.repositories.CheckinRepo;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Set;

/**
 * Created by mnikitin on 6/1/17.
 */
public class CheckinServiceImpl implements CheckinService {

    @Autowired
    private CheckinRepo checkinRepo;

    @Override
    public void approveCheckin(Manager approvingManager, Checkin checkin) {
        checkin.setApprovedBy(approvingManager);
        checkin.setApproveTime(LocalDateTime.now());
        checkinRepo.save(checkin);
    }

    @Override
    public boolean hasCheckedInToday(Associate associate) {
        Set<Checkin> checkins = checkinRepo.getAllByCheckinTimeBetween(
                LocalDateTime.of(
                        LocalDate.now(), LocalTime.MIDNIGHT
                ), LocalDateTime.now()
        );
        return (checkins != null && checkins.size() != 0);
    }

    @Override
    public void checkIn(Associate associate) throws AlreadyCheckedInException {
        // Have we already checked in today?
        Set<Checkin> checkins = checkinRepo.getAllByCheckinTimeBetween(
                LocalDateTime.of(
                        LocalDate.now(), LocalTime.MIDNIGHT
                ), LocalDateTime.now()
        );
        if(checkins != null && checkins.size() != 0)
            throw new AlreadyCheckedInException();
        Checkin checkin = new Checkin();
        checkin.setAssociate(associate);
        checkin.setCheckinTime(LocalDateTime.now());
        checkinRepo.save(checkin);
    }

    @Override
    public void checkOut(Associate associate) throws AlreadyCheckedOutException, NotCheckedInException {
        Set<Checkin> checkins = checkinRepo.getAllByCheckinTimeBetween(
                LocalDateTime.of(LocalDate.now(), LocalTime.MIDNIGHT),
                LocalDateTime.now()
        );
        if(checkins == null || checkins.size() == 0)
            throw new NotCheckedInException();
        // We assume that there's exactly one, but don't complain if there's more than one for today.
        for (Checkin checkin: checkins) {
            if(checkin.getCheckoutTime() != null)
                throw new AlreadyCheckedOutException();
            checkin.setCheckoutTime(LocalDateTime.now());
        }
        checkinRepo.save(checkins);
    }
}
