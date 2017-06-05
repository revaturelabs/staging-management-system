package com.revature.services;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.entities.InterviewQuestion;
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
		interviewQuestionRepo.saveAndFlush(interviewQ);
	}

	@Override
	public void delete(InterviewQuestion interviewQ) {
		interviewQuestionRepo.delete(interviewQ);
	}

	@Override
	public void update(InterviewQuestion interviewQ) {
		interviewQuestionRepo.saveAndFlush(interviewQ);
	}

	@Override
	public InterviewQuestion findById(long id) {
		return interviewQuestionRepo.getOne(id);
	}

	@Override
	public Set<InterviewQuestion> getAll() {
		return new HashSet<InterviewQuestion>(interviewQuestionRepo.findAll());
	}

}
