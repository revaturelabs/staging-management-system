package com.revature.sms.repositories;

import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.sms.entities.Client;

public interface ClientRepo extends JpaRepository<Client, Long>{
	Set<Client> findByPriorityTrue();

}
