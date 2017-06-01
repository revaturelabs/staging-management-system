package com.revature.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.entities.Credential;

public interface CredentialRepo extends JpaRepository<Credential, Long> {
	
	Credential findByUsernameAndPassword(String username,String password);
}
