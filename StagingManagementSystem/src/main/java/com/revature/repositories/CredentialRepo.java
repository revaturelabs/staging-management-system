package com.revature.repositories;

import com.revature.entities.Credential;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CredentialRepo extends JpaRepository<Credential, Long> {

    Credential findByUsernameAndPassword(String username, String password);
    Credential findByUsername(String username);
}
