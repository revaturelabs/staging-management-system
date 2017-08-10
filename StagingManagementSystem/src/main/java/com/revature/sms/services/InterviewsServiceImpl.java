package com.revature.sms.services;

import com.revature.sms.entities.Interview;
import com.revature.sms.repositories.InterviewsRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

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

        return new HashSet<>(interviewsRepo.findAll());
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

        LocalDateTime endOfToday = LocalDateTime.of(LocalDate.now(), LocalTime.MAX);
        return interviewsRepo.findByScheduledBetween(LocalDateTime.of(LocalDate.now(), LocalTime.MIDNIGHT), endOfToday.plusDays(5l));
    }
}
