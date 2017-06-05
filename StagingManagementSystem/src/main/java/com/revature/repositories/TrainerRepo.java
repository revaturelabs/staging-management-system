package com.revature.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.entities.Trainer;

public interface TrainerRepo extends JpaRepository<Trainer, Long>{
	Trainer findById(long id);

}
