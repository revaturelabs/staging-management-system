package com.revature.services;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.entities.InterviewQuestion;
import com.revature.exceptions.SmsCustomException;
import com.revature.exceptions.badrequests.NullReferenceException;
import com.revature.repositories.InterviewQuestionRepo;

@Service
public class InterviewQuestionServiceImpl implements InterviewQuestionService {

	@Autowired
	InterviewQuestionRepo interviewQuestionRepo;

	public InterviewQuestionServiceImpl(InterviewQuestionRepo interviewQuestionRepo) {
		super();
		this.interviewQuestionRepo = interviewQuestionRepo;
	}

	@Override
	public void add(InterviewQuestion interviewQ) {
		interviewQ = interviewQuestionRepo.saveAndFlush(interviewQ);
	}

	@Override
	public void delete(InterviewQuestion interviewQ) throws SmsCustomException{
		if(interviewQ == null){
			throw new NullReferenceException("Interview Questions is null.");
		}
		interviewQuestionRepo.delete(interviewQ);
	}

	@Override
	public void update(InterviewQuestion interviewQ) throws SmsCustomException{
		if(interviewQ == null){
			throw new NullReferenceException("Interview Question is null.");
		}
		interviewQ = interviewQuestionRepo.saveAndFlush(interviewQ);
	}

	@Override
	public InterviewQuestion findById(long id) throws SmsCustomException{
		InterviewQuestion interviewQ = interviewQuestionRepo.getOne(id);
		if(interviewQ == null){
			throw new NullReferenceException("Interview Question is null.");
		}
		return interviewQ;
	}

	@Override
	public Set<InterviewQuestion> getAll() {
		return new HashSet<InterviewQuestion>(interviewQuestionRepo.findAll());
	}

}
