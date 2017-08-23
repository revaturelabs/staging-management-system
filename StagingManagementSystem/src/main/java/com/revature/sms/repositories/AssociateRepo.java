package com.revature.sms.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.sms.entities.Associate;
import com.revature.sms.entities.AssociatesStatus;
import com.revature.sms.entities.Credential;

import java.util.Set;

public interface AssociateRepo extends JpaRepository<Associate, Long> {

    Associate getByCredential_Username(String username);
    Associate getByCredential(Credential credential);
    Set<Associate> findByBatchIsNull();
    Set<Associate> findByBatchId(Long id);
    //Our changes
    Set<Associate> findByAssociateStatus_Status(String status);
    Set<Associate> findByProjectProjectId(Long projectId);
    Set<Associate> findByProjectIsNull();
    Set<Associate> findByNameContainingIgnoreCase(String name);
}
