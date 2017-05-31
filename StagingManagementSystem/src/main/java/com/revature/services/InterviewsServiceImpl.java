package com.revature.services;

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
		// TODO Auto-generated method stub
		
	}
}
