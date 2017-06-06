package com.revature.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import com.revature.entities.Associate;
import com.revature.entities.Check;
import com.revature.entities.Manager;
import com.revature.exceptions.AlreadyCheckedInException;
import com.revature.exceptions.AlreadyCheckedOutException;
import com.revature.exceptions.NotCheckedInException;
import com.revature.repositories.AssociateRepo;
import com.revature.repositories.CheckinRepo;

/**
 * Created by Mykola Nikitin on 6/1/17. An implementation of the CheckinService.
 */
public class CheckinServiceImpl implements CheckinService {

	@Autowired
	private CheckinRepo checkinRepo;

	@Autowired
	private AssociateRepo associateRepo;

	@Override
	public void approveCheckin(Manager approvingManager, Check checkin) {
		checkin.setApprovedBy(approvingManager);
		checkin.setApproveTime(LocalDateTime.now());
		checkinRepo.save(checkin);
		checkinRepo.flush();
	}

	@Override
	public boolean hasCheckedInToday(Associate associate) {
		Set<Check> checkins = checkinRepo
				.getAllByCheckinTimeBetween(LocalDateTime.of(LocalDate.now(), LocalTime.MIDNIGHT), LocalDateTime.now());
		return (checkins != null && checkins.size() != 0);
	}

	@Override
	public void checkIn(Associate associate, LocalDateTime when) throws AlreadyCheckedInException {
		// Have we already checked in today?
		if (when == null) {
			Set<Check> checkins = checkinRepo.getAllByCheckinTimeBetween(
					LocalDateTime.of(LocalDate.now(), LocalTime.MIDNIGHT), LocalDateTime.now());
			if (checkins != null && checkins.size() != 0)
				throw new AlreadyCheckedInException();
			when = LocalDateTime.now();
		}
		Check checkin = new Check();
		checkin.setAssociate(associate);
		checkin.setCheckinTime(when);
		checkinRepo.save(checkin);
	}

	@Override
	public void checkOut(Associate associate) throws AlreadyCheckedOutException, NotCheckedInException {
		Set<Check> checkins = checkinRepo
				.getAllByCheckinTimeBetween(LocalDateTime.of(LocalDate.now(), LocalTime.MIDNIGHT), LocalDateTime.now());
		if (checkins == null || checkins.size() == 0)
			throw new NotCheckedInException();
		// We assume that there's exactly one, but don't complain if there's
		// more than one for today.
		for (Check checkin : checkins) {
			if (checkin.getCheckoutTime() != null)
				throw new AlreadyCheckedOutException();
			checkin.setCheckoutTime(LocalDateTime.now());
		}
		checkinRepo.save(checkins);
	}

	@Override
	public void checkOut(Check checkin, LocalDateTime when) {
		checkin.setCheckoutTime(when);
		checkinRepo.save(checkin);
	}

	@Override
	public void checkOut(long associateId) throws AlreadyCheckedOutException, NotCheckedInException {
		Associate associate = associateRepo.getOne(associateId);
		checkOut(associate);
	}
}
