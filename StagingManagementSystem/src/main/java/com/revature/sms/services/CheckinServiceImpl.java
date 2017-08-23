package com.revature.sms.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.revature.sms.entities.Associate;
import com.revature.sms.entities.Checkin;
import com.revature.sms.entities.Manager;
import com.revature.sms.exceptions.AlreadyCheckedInException;
import com.revature.sms.exceptions.AlreadyCheckedOutException;
import com.revature.sms.exceptions.NotCheckedInException;
import com.revature.sms.exceptions.NotLoggedInException;
import com.revature.sms.repositories.AssociateRepo;
import com.revature.sms.repositories.CheckinRepo;

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
		if (associate == null)
			return null;
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
		Set<Checkin> checkins = checkinRepo
				.getAllByCheckinTimeBetween(LocalDateTime.of(LocalDate.now(), LocalTime.MIDNIGHT), LocalDateTime.now());
		for (Checkin check : checkins) {
			if (check.getAssociate().getId() == associate.getId()) {
				return checkins != null && !checkins.isEmpty();
			}
		}
		return false;
	}

	@Override
	public boolean hasCheckedInOnDate(Associate associate, LocalDateTime date) {
		LocalDateTime start = LocalDateTime.of(date.toLocalDate(), LocalTime.MIN);
		LocalDateTime end = LocalDateTime.of(date.toLocalDate(), LocalTime.MAX);
		Set<Checkin> checkins = checkinRepo.getAllByCheckinTimeBetween(start, end);

		return checkins != null && checkins.isEmpty();
	}

	@Override
	public boolean hasCheckedInToday() throws NotLoggedInException {
		String user = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Associate associate = associateRepo.getByCredential_Username(user);
		if (user == null || associate == null)
			throw new NotLoggedInException();
		return hasCheckedInToday(associate);
	}

	@Override
	public void checkIn(Associate associate, LocalDateTime nwhen) throws AlreadyCheckedInException {
		// Have we already checked in today?
		LocalDateTime when = nwhen;
		if (when == null) {
			Set<Checkin> checkins = checkinRepo.getAllByCheckinTimeBetween(
					LocalDateTime.of(LocalDate.now(), LocalTime.MIDNIGHT), LocalDateTime.now());
			if (checkins != null && checkins.isEmpty())
				throw new AlreadyCheckedInException();
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
		if (user == null)
			throw new NotLoggedInException();
		Associate associate = associateRepo.getByCredential_Username(user.getUsername());
		if (associate == null)
			throw new NotLoggedInException();
		checkIn(associate, LocalDateTime.now());
	}

	@Override
	public void checkOut(Associate associate) throws AlreadyCheckedOutException, NotCheckedInException {
		Set<Checkin> checkins = checkinRepo
				.getAllByCheckinTimeBetween(LocalDateTime.of(LocalDate.now(), LocalTime.MIDNIGHT), LocalDateTime.now());
		if (checkins == null || checkins.isEmpty())
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
		return new HashSet<>(checkinRepo.findAll());
	}

	@Override
	public Set<Checkin> getTodaysCheckins() {
		return checkinRepo.getAllByCheckinTimeBetween(LocalDateTime.of(LocalDate.now(), LocalTime.MIDNIGHT),
				LocalDateTime.now());
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
