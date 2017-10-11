package com.revature.sms.services;

import java.util.Set;

import com.revature.sms.entities.Trainer;

public interface TrainerService {

    public void add(Trainer trainer);
    public void delete(Trainer trainer);
    public void update(Trainer trainer);
    public Trainer findById(long id);
    public Set<Trainer> getAll();
    void addTrainers(Set<Trainer> trainers);
}
