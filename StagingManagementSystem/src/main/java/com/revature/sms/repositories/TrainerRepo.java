package com.revature.sms.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.sms.entities.Trainer;

import javax.transaction.Transactional;

@Transactional
public interface TrainerRepo extends JpaRepository<Trainer, Long> {

}
