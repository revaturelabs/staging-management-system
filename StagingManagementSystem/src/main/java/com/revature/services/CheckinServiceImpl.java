package com.revature.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.entities.Associate;
import com.revature.entities.Checkin;
import com.revature.entities.Manager;
import com.revature.exceptions.AlreadyCheckedInException;
import com.revature.exceptions.AlreadyCheckedOutException;
import com.revature.exceptions.NotCheckedInException;
import com.revature.repositories.AssociateRepo;
import com.revature.repositories.CheckinRepo;

/**
 * Created by Mykola Nikitin on 6/1/17. An implementation of the CheckinService.
 */
@Service
public class CheckinServiceImpl implements CheckinService {

	@Autowired
	private CheckinRepo checkinRepo;

	@Autowired
	private AssociateRepo associateRepo;

	@Override
	public void approveCheckin(Manager approvingManager, Checkin checkin) {
		checkin.setApprovedBy(approvingManager);
		checkin.setApproveTime(LocalDateTime.now());
		checkinRepo.save(checkin);
		checkinRepo.flush();
	}

	@Override
	public boolean hasCheckedInToday(Associate associate) {
		Set<Checkin> checkins = checkinRepo
				.getAllByCheckinTimeBetween(LocalDateTime.of(LocalDate.now(), LocalTime.MIDNIGHT), LocalDateTime.now());
		return (checkins != null && checkins.size() != 0);
	}

	@Override
	public void checkIn(Associate associate, LocalDateTime when) throws AlreadyCheckedInException {
		// Have we already checked in today?
		if (when == null) {
			Set<Checkin> checkins = checkinRepo.getAllByCheckinTimeBetween(
					LocalDateTime.of(LocalDate.now(), LocalTime.MIDNIGHT), LocalDateTime.now());
			if (checkins != null && checkins.size() != 0)
				throw new AlreadyCheckedInException();
			when = LocalDateTime.now();
		}
		Checkin checkin = new Checkin();
		checkin.setAssociate(associate);
		checkin.setCheckinTime(when);
		checkinRepo.save(checkin);
	}

	@Override
	public void checkOut(Associate associate) throws AlreadyCheckedOutException, NotCheckedInException {
		Set<Checkin> checkins = checkinRepo
				.getAllByCheckinTimeBetween(LocalDateTime.of(LocalDate.now(), LocalTime.MIDNIGHT), LocalDateTime.now());
		if (checkins == null || checkins.size() == 0)
			throw new NotCheckedInException();
		// We assume that there's exactly one, but don't complain if there's
		// more than one for today.
		for (Checkin checkin : checkins) {
			if (checkin.getCheckoutTime() != null)
				throw new AlreadyCheckedOutException();
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
    
	public void checkOut(long associateId) throws AlreadyCheckedOutException, NotCheckedInException {
		Associate associate = associateRepo.getOne(associateId);
		checkOut(associate);
	}
}
