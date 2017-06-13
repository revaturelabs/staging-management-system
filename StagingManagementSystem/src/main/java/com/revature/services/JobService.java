package com.revature.services;

import java.util.Set;

import com.revature.entities.Job;

public interface JobService {

	public void add(Job job);

	public void delete(Job job);

	public void update(Job job);

	public Job findById(long id);

	public Set<Job> getAll();

}
