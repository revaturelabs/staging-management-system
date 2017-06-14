package com.revature.services;

import com.revature.entities.Trainer;
import com.revature.repositories.TrainerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class TrainerServiceImpl implements TrainerService {

    @Autowired
    TrainerRepo trainerRepo;

    public TrainerServiceImpl(TrainerRepo trainerRepo) {

        super();
        this.trainerRepo = trainerRepo;
    }

    @Override
    public void add(Trainer trainer) {

        trainerRepo.saveAndFlush(trainer);
    }

    @Override
    public Set<Trainer> getAll() {

        return new HashSet<Trainer>(trainerRepo.findAll());
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

    @Override
    public void addTrainers(Set<Trainer> trainers) {

        trainers.forEach((Trainer trainer) -> {
            trainerRepo.saveAndFlush(trainer);
        });
    }
}
