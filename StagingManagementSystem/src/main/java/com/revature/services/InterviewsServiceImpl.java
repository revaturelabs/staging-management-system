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

	// public InterviewsServiceImpl() {
	// super();
	// }

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
	public Interviews findByAssociate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Interviews findByClient() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Interviews findByStatus() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Interviews findByScheduled(LocalDateTime dateandtime) {
		// TODO Auto-generated method stub
		return null;
	}

	// @Override
	// public Interviews findByAssociate() {
	// return interviewsRepo.findByAssociate();
	// }
	//
	// @Override
	// public Interviews findByClient() {
	// return interviewsRepo.findByClient();
	// }
	//
	// @Override
	// public Interviews findByStatus() {
	// return interviewsRepo.findByStatus();
	// }
	//
	// @Override
	// public Interviews findByScheduled(LocalDateTime dateandtime) {
	// return interviewsRepo.findByScheduled(dateandtime);
	// }
}
