package com.revature.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.entities.SmsEntities;

public interface TrainerRepo extends JpaRepository<SmsEntities, Long>
{

}
