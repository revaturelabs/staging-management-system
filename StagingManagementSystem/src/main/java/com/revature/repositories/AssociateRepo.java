package com.revature.repositories;

import com.revature.entities.Associate;
import com.revature.entities.Credential;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface AssociateRepo extends JpaRepository<Associate, Long> {

    Associate getByCredential_Username(String username);
    Set<Associate> findByActive(boolean bool);
    Associate getByCredential(Credential credential);
    Set<Associate> findAssociatesByActiveTrue();
    Set<Associate> findByBatchIsNull();
    Set<Associate> findByBatchId(Long id);
}
