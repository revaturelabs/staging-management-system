package com.revature.repositories;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.entities.Associate;
import com.revature.entities.Credential;


public interface AssociateRepo extends JpaRepository<Associate, Long> {
    Associate getByCredential_Username(String username);
    
    Associate getByCredential(Credential credential);

    Set<Associate> findAssociatesByActiveTrue();

	Set<Associate> findByBatchIsNull();

	Set<Associate> findByBatchId(Long id);
}
