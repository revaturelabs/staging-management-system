package com.revature.repositories;

import com.revature.entities.Credential;
import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.entities.Associate;

public interface AssociateRepo extends JpaRepository<Associate, Long> {
    Associate getByCredential_Username(String username);
    
    Associate getByCredential(Credential credential);
}
