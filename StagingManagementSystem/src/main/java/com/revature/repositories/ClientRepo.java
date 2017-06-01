package com.revature.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.entities.Client;

public interface ClientRepo extends JpaRepository<Client, Long>{

}
