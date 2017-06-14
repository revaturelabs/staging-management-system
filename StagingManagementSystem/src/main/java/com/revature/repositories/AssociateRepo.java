package com.revature.repositories;

import com.revature.entities.Associate;
import com.revature.entities.Credential;
import org.springframework.data.jpa.repository.JpaRepository;

<<<<<<< HEAD
import java.util.Set;

public interface AssociateRepo extends JpaRepository<Associate, Long> {

    Associate getByCredential_Username(String username);
    Set<Associate> findByActive(boolean bool);
    Associate getByCredential(Credential credential);
    Set<Associate> findAssociatesByActiveTrue();
    Set<Associate> findByBatchIsNull();
    Set<Associate> findByBatchId(Long id);
=======
public interface AssociateRepo extends JpaRepository<Associate, Long> {
	Associate getByCredential_Username(String username);
	
	Set<Associate> findByActive(boolean bool);

	Associate getByCredential(Credential credential);

	Set<Associate> findAssociatesByActiveTrue();

	Set<Associate> findByBatchIsNull();

	Set<Associate> findByBatchId(Long id);
>>>>>>> a4e500d19fec27f2876652a33c6b8629932fc34d
}
