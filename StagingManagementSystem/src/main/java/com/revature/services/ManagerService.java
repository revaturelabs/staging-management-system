package com.revature.services;

import java.util.Set;

import com.revature.entities.Credential;
import com.revature.entities.Manager;
import com.revature.exceptions.SMSCustomException;

public interface ManagerService {

	// c
	void add(Manager manager) throws SMSCustomException;
	
	// r
	Manager getById(long id) throws SMSCustomException;
	Manager getByCredential(Credential credential) throws SMSCustomException;
	Set<Manager> getAll();
	
	// u
	void update(Manager manager) throws SMSCustomException;
	
	// d
	void remove(Manager manager) throws SMSCustomException;
}
