package com.revature.services;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import com.revature.entities.Credential;
import com.revature.entities.Manager;
import com.revature.exceptions.NonUniqueException;
import com.revature.exceptions.NullReferenceException;
import com.revature.exceptions.SMSCustomException;
import com.revature.repositories.CredentialRepo;
import com.revature.repositories.ManagerRepo;

public class ManagerServiceImpl implements ManagerService {

	@Autowired
	private ManagerRepo managerRepo;

	@Autowired
	private CredentialRepo credentialRepo;

	public ManagerServiceImpl(ManagerRepo managerRepo, CredentialRepo credentialRepo) {
		super();
		this.managerRepo = managerRepo;
		this.credentialRepo = credentialRepo;
	}

	public ManagerServiceImpl() {
		super();
	}

	// c
	@Override
	public void add(Manager manager) throws SMSCustomException {
		
		if (manager == null) {
			throw new NullReferenceException("Manager is null.");
		}
		
		// Validate manager name.
		manager.validate();

		Credential credential = manager.getCredential();
		if (credential == null) {
			throw new NullReferenceException("Manager credential is null.");
		}
		
		// Validate credential members. Credential needs to implement SmsValidatable
		//
		//
		
		if (credentialRepo.findByUsername(credential.getUsername()) != null) {
			throw new NonUniqueException("Username already exists.");
		}
		
		credential = credentialRepo.save(credential);
		manager.setCredential(credential);
		manager = managerRepo.saveAndFlush(manager);

	}

	// r
	@Override
	public Manager getById(long id) throws SMSCustomException {
		
		Manager manager = managerRepo.getOne(id);
		
		if (manager == null) {
			throw new NullReferenceException("Manager ID not found.");
		}
		
		return manager;
	}

	@Override
	public Manager getByCredential(Credential credential) throws SMSCustomException {
		
		Manager manager = managerRepo.findByCredentialUsernameAndCredentialPassword(credential.getUsername(),
				credential.getPassword());
		
		if (manager == null) {
			throw new NullReferenceException("A manager with those credentials could not be found.");
		}
		
		return manager;
	}

	@Override
	public Set<Manager> getAll() {
		
		Set<Manager> managers = new HashSet<Manager>(managerRepo.findAll());
		// Returns empty set if none found?
	
		return managers;
	}

	// u
	@Override
	public void update(Manager manager) throws SMSCustomException {
	
		if (manager == null) {
			throw new NullReferenceException("Manager is null.");
		}
		
		// Validate managers name.
		manager.validate();

		Credential credential = manager.getCredential();
		
		if (credential == null) {
			throw new NullReferenceException("Manager credential is null.");
		}
		
		// Validate credential members. Credential needs to implement SmsValidatable.
		//
		//

		credential = credentialRepo.save(credential);
		manager.setCredential(credential);
		manager = managerRepo.saveAndFlush(manager);
	}

	// d
	@Override
	public void remove(Manager manager) throws SMSCustomException {
		
		if (manager == null) {
			throw new NullReferenceException("Manager is null.");
		}
		
		Credential credential = manager.getCredential();
		
		if (credential == null) {
			throw new NullReferenceException("Manager credential is null.");
		}
		
		credentialRepo.delete(credential);
		managerRepo.delete(manager);
	}
}
