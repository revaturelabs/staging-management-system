package com.revature.services;

import java.util.List;

import com.revature.entities.Trainer;

public interface TrainerService
{
	public void add(Trainer trainer);
	public void delete(Trainer trainer);
	
	public void update(Trainer trainer);
	
	public Trainer findById(long id);
	public List<Trainer> getAll();
}
