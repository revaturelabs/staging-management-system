package com.revature.sms.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.sms.entities.Marketer;

public interface MarketerRepo extends JpaRepository<Marketer, Long> {

}
