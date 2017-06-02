package com.revature.services;

import java.time.LocalDateTime;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.entities.Interviews;
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
	public void add(Interviews interviews) {
		interviewsRepo.saveAndFlush(interviews);
	}

	@Override
	public Interviews findById(long id) {
		return interviewsRepo.getOne(id);
	}

	@Override
	public List<Interviews> getAll() {
		return interviewsRepo.findAll();
	}

	@Override
	public void update(Interviews interviews) {
		interviewsRepo.saveAndFlush(interviews);
	}

	@Override
	public void delete(Interviews interviews) {
		interviewsRepo.delete(interviews);
	}

	@Override
	public Interviews findByAssociateId(long id) {
		return interviewsRepo.findByAssociateId(id);
	}

	@Override
	public Interviews findByClientId(long id) {
		return interviewsRepo.findByClientId(id);
	}
	
	 @Override
	 public Interviews findByInterviewStatus(long id) {
		 return interviewsRepo.findByInterviewStatusId(id);
	 }
	
	 @Override
	 public Interviews findByScheduled(LocalDateTime dateandtime) {
	 return interviewsRepo.findByScheduled(dateandtime);
	 }
}
