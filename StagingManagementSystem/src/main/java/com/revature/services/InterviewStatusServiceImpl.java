package com.revature.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.entities.InterviewStatuses;
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
	public void add(InterviewStatuses interviewStatus) {
		interviewStatusRepo.saveAndFlush(interviewStatus);
	}

	@Override
	public InterviewStatuses findById(long id) {
		return interviewStatusRepo.getOne(id);
	}

	@Override
	public List<InterviewStatuses> getAll() {
		return interviewStatusRepo.findAll();
	}

	@Override
	public void update(InterviewStatuses interviewStatus) {
		interviewStatusRepo.saveAndFlush(interviewStatus);
	}

	@Override
	public void delete(InterviewStatuses interviewStatus) {
		interviewStatusRepo.delete(interviewStatus);
	}
	
	@Override
	public InterviewStatuses findByStatus(String status) {
		 return interviewStatusRepo.findByStatus(status);
	}


}
