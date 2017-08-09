package com.revature.services;

import com.revature.entities.Trainer;

import java.util.Set;

public interface TrainerService {

    public void add(Trainer trainer);
    public void delete(Trainer trainer);
    public void update(Trainer trainer);
    public Trainer findById(long id);
    public Set<Trainer> getAll();
    void addTrainers(Set<Trainer> trainers);
}
