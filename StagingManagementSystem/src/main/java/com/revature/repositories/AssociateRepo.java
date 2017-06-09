package com.revature.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.revature.entities.Associate;
import com.revature.entities.Credential;

public interface AssociateRepo extends JpaRepository<Associate, Long> {
    Associate getByCredential_Username(String username);
    
    Associate getByCredential(Credential credential);
}
