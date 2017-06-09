package com.revature.repositories;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.entities.Trainer;

@Transactional
public interface TrainerRepo extends JpaRepository<Trainer, Long> {
   
}
