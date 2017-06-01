package com.revature.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.entities.InterviewStatus;
import com.revature.repositories.InterviewStatusRepo;
@Service
public class InterviewStatusServiceImpl implements InterviewStatusService {
	@Autowired
	InterviewStatusRepo interviewStatusRepo;
	
	public InterviewStatusServiceImpl(InterviewStatusRepo interviewStatusRepo){
		super();
		this.interviewStatusRepo = interviewStatusRepo;
	}
	
	@Override
	public void add(InterviewStatus interviewStatus) {
		interviewStatusRepo.saveAndFlush(interviewStatus);
	}

	@Override
	public InterviewStatus findById(long id) {
		return interviewStatusRepo.getOne(id);
	}

	@Override
	public List<InterviewStatus> getAll() {
		return interviewStatusRepo.findAll();
	}

	@Override
	public void update(InterviewStatus interviewStatus) {
		interviewStatusRepo.saveAndFlush(interviewStatus);
	}

	@Override
	public void delete(InterviewStatus interviewStatus) {
		interviewStatusRepo.delete(interviewStatus);
	}
	//
	// @Override
	// public InterviewStatus findByInterviews() {
	// return interviewStatusRepo.findByInterviews();
	// }
	//
	// @Override
	// public InterviewStatus findByStatus(String status) {
	// return interviewStatusRepo.findByStatus(status);
	// }

	@Override
	public InterviewStatus findByInterviews() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InterviewStatus findByStatus(String status) {
		// TODO Auto-generated method stub
		return null;
	}
}
