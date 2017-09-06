package com.revature.sms.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.sms.entities.ClientQuestion;

public interface ClientQRepo extends JpaRepository<ClientQuestion, Long> {

}
