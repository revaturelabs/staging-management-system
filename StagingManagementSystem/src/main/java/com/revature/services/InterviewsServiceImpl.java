package com.revature.services;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.entities.Interview;
import com.revature.repositories.InterviewsRepo;

@Service
public class InterviewsServiceImpl implements InterviewsService {

	@Autowired
	private InterviewsRepo interviewsRepo;

	public InterviewsServiceImpl(InterviewsRepo interviewsRepo) {
		super();
		this.interviewsRepo = interviewsRepo;
	}

	@Override
	@Transactional
	public void add(Interview interviews) {
		interviewsRepo.saveAndFlush(interviews);
	}

	@Override
	public Interview findById(long id) {
		return interviewsRepo.getOne(id);
	}

	@Override
	public Set<Interview> getAll() {
		return new HashSet<Interview>(interviewsRepo.findAll());
	}

	@Override
	public void update(Interview interviews) {
		interviewsRepo.saveAndFlush(interviews);
	}

	@Override
	public void delete(Interview interviews) {
		interviewsRepo.delete(interviews);
	}

	@Override
	public Set<Interview> findByAssociateId(long id) {
		return interviewsRepo.findByAssociateId(id);
	}

	@Override
	public Set<Interview> findByClientId(long id) {
		return interviewsRepo.findByClientId(id);
	}

	@Override
	public Set<Interview> findByInterviewStatus(long id) {
		return interviewsRepo.findByInterviewStatusId(id);
	}

	@Override
	public Set<Interview> nextFiveDays() {
		LocalDateTime now = LocalDateTime.now();

		// Subtracted the hours so we see all interviews that were today even if
		// they happened before the current time
		// Then look at 5 days in the future of the current time
		return interviewsRepo.findByScheduledBetween(now.minusHours(now.getHour()), now.plusDays(5l));
	}

}