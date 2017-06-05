package com.revature.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.entities.Associate;

public interface AssociateRepo extends JpaRepository<Associate, Long> {
    Associate getByCredential_Username(String username);
    
    Associate getByCredential_Id(Long id);
}
