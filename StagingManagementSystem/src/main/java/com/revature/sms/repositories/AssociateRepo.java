package com.revature.sms.repositories;

import com.revature.sms.entities.AssociatesStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.sms.entities.Associate;
import com.revature.sms.entities.Credential;

import java.util.Set;

public interface AssociateRepo extends JpaRepository<Associate, Long> {

    Associate getByCredential_Username(String username);

    //we took this out
    //Set<Associate> findByActive(boolean bool);

    Associate getByCredential(Credential credential);

    //thinking
   // Set<Associate> findAssociatesByIsActiveTrue();

    Set<Associate> findByBatchIsNull();
    Set<Associate> findByBatchId(Long id);
    Set<Associate> findByProjectIsNull();

    //our change
    //Set<Associate> findByAssociateStatus(AssociatesStatus status);

    Set<Associate> findByAssociateStatusStatus(String status);
}
