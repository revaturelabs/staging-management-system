package com.revature.services;

import com.revature.entities.Associate;
import com.revature.entities.Checkin;
import com.revature.entities.Manager;
import com.revature.exceptions.AlreadyCheckedInException;
import com.revature.exceptions.AlreadyCheckedOutException;
import com.revature.exceptions.NotCheckedInException;
import com.revature.exceptions.NotLoggedInException;
import com.revature.repositories.AssociateRepo;
import com.revature.repositories.CheckinRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Mykola Nikitin on 6/1/17. An implementation of the CheckinService.
 */
@Service
@Component
public class CheckinServiceImpl implements CheckinService {

    @Autowired
    private CheckinRepo checkinRepo;

    @Autowired
    private AssociateRepo associateRepo;

    @Override
    public Set<Checkin> getAllForAssociate(String username) {

        if (username == null || "".equals(username)) {
            return null;
        }
        return getAllForAssociate(associateRepo.getByCredential_Username(username));
    }

    @Override
    public Set<Checkin> getAllForAssociate(Associate associate) {

        if (associate == null) return null;
        return checkinRepo.getAllByAssociate_Id(associate.getId());
    }

    @Override
    public void approveCheckin(Manager approvingManager, Checkin checkin) {

        checkin.setApprovedBy(approvingManager);
        checkin.setApproveTime(LocalDateTime.now());
        checkinRepo.save(checkin);
        checkinRepo.flush();
    }

    @Override
    public boolean hasCheckedInToday(Associate associate) {

        Set<Checkin> checkins = checkinRepo.getAllByCheckinTimeBetween(LocalDateTime.of(LocalDate.now(), LocalTime.MIDNIGHT), LocalDateTime.now());
        return (checkins != null && checkins.isEmpty());
    }

    @Override
    public boolean hasCheckedInToday() throws NotLoggedInException {

        String user = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Associate associate = associateRepo.getByCredential_Username(user);
        if (user == null || associate == null) throw new NotLoggedInException();
        return hasCheckedInToday(associate);
    }

    @Override
    public void checkIn(Associate associate, LocalDateTime nwhen) throws AlreadyCheckedInException {
        // Have we already checked in today?
        LocalDateTime when = nwhen;
        if (when == null) {
            Set<Checkin> checkins = checkinRepo.getAllByCheckinTimeBetween(LocalDateTime.of(LocalDate.now(), LocalTime.MIDNIGHT), LocalDateTime.now());
            if (checkins != null && checkins.isEmpty()) throw new AlreadyCheckedInException();
            when = LocalDateTime.now();
        }
        Checkin checkin = new Checkin();
        checkin.setAssociate(associate);
        checkin.setCheckinTime(when);
        checkinRepo.save(checkin);
    }

    @Override
    public void checkIn() throws AlreadyCheckedInException, NotLoggedInException {

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (user == null) throw new NotLoggedInException();
        Associate associate = associateRepo.getByCredential_Username(user.getUsername());
        if (associate == null) throw new NotLoggedInException();
        checkIn(associate, LocalDateTime.now());
    }

    @Override
    public void checkOut(Associate associate) throws AlreadyCheckedOutException, NotCheckedInException {

        Set<Checkin> checkins = checkinRepo.getAllByCheckinTimeBetween(LocalDateTime.of(LocalDate.now(), LocalTime.MIDNIGHT), LocalDateTime.now());
        if (checkins == null || checkins.isEmpty()) throw new NotCheckedInException();
        // We assume that there's exactly one, but don't complain if there's
        // more than one for today.
        for (Checkin checkin : checkins) {
            if (checkin.getCheckoutTime() != null) throw new AlreadyCheckedOutException();
            checkin.setCheckoutTime(LocalDateTime.now());
        }
        checkinRepo.save(checkins);
    }

    @Override
    public void checkOut(Checkin checkin, LocalDateTime when) {

        checkin.setCheckoutTime(when);
        checkinRepo.save(checkin);
    }

    @Override
    public void addcheckins(Set<Checkin> checkins) {
        // TODO Auto-generated method stub
    }

    @Override
    public Set<Checkin> getAll() {

        Set<Checkin> checkin = new HashSet<Checkin>(checkinRepo.findAll());
        return checkin;
    }

    @Override
    public Set<Checkin> getTodaysCheckins() {

        Set<Checkin> checkins = checkinRepo.getAllByCheckinTimeBetween(LocalDateTime.of(LocalDate.now(), LocalTime.MIDNIGHT), LocalDateTime.now());
        return checkins;
    }

    @Override
    public Checkin findById(long id) {

        return checkinRepo.getOne(id);
    }

    @Override
    public void update(Checkin checkin) {

        checkinRepo.saveAndFlush(checkin);
    }

    @Override
    public void add(Checkin checkin) {

        checkinRepo.saveAndFlush(checkin);
    }

    @Override
    public void checkOut(long associateId) throws AlreadyCheckedOutException, NotCheckedInException {

        Associate associate = associateRepo.getOne(associateId);
        checkOut(associate);
    }

    @Override
    public void approveMultiple(Set<Checkin> checkins, Manager manager) {

        LocalDateTime now = LocalDateTime.now();
        for (Checkin checkin : checkins) {
            checkin.setApprovedBy(manager);
            checkin.setApproveTime(now);
            checkinRepo.saveAndFlush(checkin);
        }
    }
}
