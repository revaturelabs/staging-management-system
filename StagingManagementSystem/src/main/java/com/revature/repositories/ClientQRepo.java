package com.revature.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.entities.ClientQuestion;

public interface ClientQRepo extends JpaRepository<ClientQuestion, Long> {
}
