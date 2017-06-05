package com.revature.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.entities.Manager;

public interface ManagerRepo extends JpaRepository<Manager, Long> {

	Manager findByCredentialUsernameAndCredentialPassword(String username, String password);

	Manager getByCredential_Username(String username);
}