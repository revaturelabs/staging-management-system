package com.revature.repositories;

import com.revature.entities.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

@Transactional
public interface TrainerRepo extends JpaRepository<Trainer, Long> {

}
