package com.revature.sms.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.sms.entities.Credential;

public interface CredentialRepo extends JpaRepository<Credential, Long> {

    Credential findByUsernameAndPassword(String username, String password);
    Credential findByUsername(String username);
}
