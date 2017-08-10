package com.revature.sms.services;

import com.revature.sms.entities.InterviewQuestion;
import com.revature.sms.repositories.InterviewQuestionRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class InterviewQuestionServiceImpl implements InterviewQuestionService {

    @Autowired
    private InterviewQuestionRepo interviewQuestionRepo;

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
