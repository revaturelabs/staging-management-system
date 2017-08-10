package com.revature.sms.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.sms.entities.Credential;
import com.revature.sms.entities.Manager;

public interface ManagerRepo extends JpaRepository<Manager, Long> {

    Manager findByCredentialUsernameAndCredentialPassword(String username, String password);
    Manager getByCredential_Username(String username);
    Manager getByCredential(Credential credential);
}