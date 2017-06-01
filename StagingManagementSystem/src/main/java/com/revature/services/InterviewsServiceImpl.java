package com.revature.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.entities.Interviews;
import com.revature.repositories.InterviewsRepo;

@Service
public class InterviewsServiceImpl implements InterviewsService{

	@Autowired
	InterviewsRepo interviewsRepo;
	
	public InterviewsServiceImpl(InterviewsRepo interviewsRepo) {
		super();
		this.interviewsRepo = interviewsRepo;
	}

	@Override
	public void add(Interviews interviews) {
		interviewsRepo.saveAndFlush(interviews);
	}

	@Override
	public Interviews findById(long id) {		
		return interviewsRepo.findById(id);
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
	public Interviews findByAssociate() {
		return interviewsRepo.findByAssociate();
	}

	@Override
	public Interviews findByClient() {
		return interviewsRepo.findByClient();
	}

	@Override
	public Interviews findByStatus() {
		return interviewsRepo.findByStatus();
	}

	@Override
	public Interviews findByScheduled(LocalDateTime dateandtime) {
		return interviewsRepo.findByScheduled(dateandtime);
	}
}
