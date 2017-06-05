package com.revature.services;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.entities.Credential;
import com.revature.entities.Manager;
import com.revature.exceptions.SmsCustomException;
import com.revature.exceptions.badrequests.NonUniqueException;
import com.revature.exceptions.badrequests.NullReferenceException;
import com.revature.repositories.CredentialRepo;
import com.revature.repositories.ManagerRepo;

@Service
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
	public void add(Manager manager) throws SmsCustomException {

		if (manager == null) {
			throw new NullReferenceException("Manager is null.");
		}

		manager.validate();

		Credential credential = manager.getCredential();
		if (credential == null) {
			throw new NullReferenceException("Manager credential is null.");
		}

		// Validate credential members. Credential needs to implement
		// SmsValidatable
		//
		//

		if (credentialRepo.findByUsername(credential.getUsername()) != null) {
			throw new NonUniqueException("Manager credential username already exists.");
		}

		credential = credentialRepo.save(credential);
		manager.setCredential(credential);
		manager = managerRepo.saveAndFlush(manager);

	}

	// r
	@Override
	public Manager getById(long id) throws SmsCustomException {

		Manager manager = managerRepo.getOne(id);

		if (manager == null) {
			throw new NullReferenceException("Manager ID could not be found.");
		}

		return manager;
	}

	@Override
	public Manager getByCredential(Credential credential) throws SmsCustomException {

		if (credential == null) {
			throw new NullReferenceException("Passed in credential argument is null.");
		}
		Manager manager = managerRepo.findByCredentialUsernameAndCredentialPassword(credential.getUsername(),
				credential.getPassword());

		if (manager == null) {
			throw new NullReferenceException("No manager found with given credential.");
		}

		return manager;
	}

	@Override
	public Set<Manager> getAll() {

		Set<Manager> managers = new HashSet<Manager>(managerRepo.findAll());

		return managers;
	}

	// u
	@Override
	public void update(Manager manager) throws SmsCustomException {

		if (manager == null) {
			throw new NullReferenceException("Manager is null.");
		}

		manager.validate();

		Credential credential = manager.getCredential();

		if (credential == null) {
			throw new NullReferenceException("Manager credential is null.");
		}

		// Validate credential members. Credential needs to implement
		// SmsValidatable.
		//
		//

		credential = credentialRepo.save(credential);
		manager.setCredential(credential);
		manager = managerRepo.saveAndFlush(manager);
	}

	// d
	@Override
	public void remove(Manager manager) throws SmsCustomException {

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
