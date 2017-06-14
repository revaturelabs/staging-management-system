package com.revature.repositories;

import com.revature.entities.Credential;
import com.revature.entities.Manager;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManagerRepo extends JpaRepository<Manager, Long> {

    Manager findByCredentialUsernameAndCredentialPassword(String username, String password);
    Manager getByCredential_Username(String username);
    Manager getByCredential(Credential credential);
}