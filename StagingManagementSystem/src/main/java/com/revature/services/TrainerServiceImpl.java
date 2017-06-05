package com.revature.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.entities.Trainer;
import com.revature.repositories.TrainerRepo;

@Service
public class TrainerServiceImpl implements TrainerService {
	@Autowired
	TrainerRepo trainerRepo;
	
	public TrainerServiceImpl(TrainerRepo trainerRepo){
		super();
		this.trainerRepo = trainerRepo;		
	}
	
	@Override
	public void add(Trainer trainer) {
		trainerRepo.saveAndFlush(trainer);
	}

	@Override
	public List<Trainer> getAll() {
		return trainerRepo.findAll();
	}

	@Override
	public Trainer findById(long id) {
		return trainerRepo.getOne(id);
	}

	@Override
	public void delete(Trainer trainer) {
		trainerRepo.delete(trainer);		
	}

	@Override
	public void update(Trainer trainer) {
		trainerRepo.saveAndFlush(trainer);
	}
	
}
