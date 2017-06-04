package com.revature.services;

import java.util.Set;

import org.springframework.stereotype.Service;

import com.revature.entities.Trainer;
import com.revature.repositories.TrainerRepo;

@Service
public class TrainerServiceImpl implements TrainerService {

	TrainerRepo trainerRepo;

	public TrainerServiceImpl(TrainerRepo trainerRepo) {
		super();
		this.trainerRepo = trainerRepo;
	}

	@Override
	public void addTrainers(Set<Trainer> trainers) {
		trainers.forEach((Trainer trainer) -> {
			trainerRepo.saveAndFlush(trainer);
		});
	}
}
