package com.revature.services;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.entities.Job;
import com.revature.repositories.JobRepo;

@Service
public class JobServiceImpl implements JobService {
	@Autowired
	JobRepo jobRepo;

	public JobServiceImpl(JobRepo jobRepo) {
		super();
		this.jobRepo = jobRepo;
	}

	@Override
	public void add(Job job) {
		jobRepo.saveAndFlush(job);
	}

	@Override
	public Set<Job> getAll() {
		return new HashSet<Job>(jobRepo.findAll());
	}

	@Override
	public Job findById(long id) {
		return jobRepo.getOne(id);
	}

	@Override
	public void delete(Job job) {
		jobRepo.delete(job);
	}

	@Override
	public void update(Job job) {
		jobRepo.saveAndFlush(job);
	}
}
