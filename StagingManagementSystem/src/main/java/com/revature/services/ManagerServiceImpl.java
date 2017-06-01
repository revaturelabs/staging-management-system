package com.revature.services;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import com.revature.entities.Credential;
import com.revature.entities.Manager;
import com.revature.exceptions.SMSCustomException;
import com.revature.exceptions.entities.NonUniqueException;
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
	public void add(Manager manager) throws SMSCustomException{
		Credential credentials = manager.getCredential();
		if(credentialRepo.findByUsernameAndPassword(credentials.getUsername(), credentials.getPassword())!=null){
			throw new NonUniqueException("Credential combination already exists.");
		}
		credentialRepo.save(credentials);
		managerRepo.saveAndFlush(manager);

	}

	// r
	@Override
	public Manager getById(long id) {
		Manager manager = managerRepo.getOne(id);
		return manager;
	}

	@Override
	public Manager getByCredential(Credential credential) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Manager> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	// u
	@Override
	public void update(Manager manager) {
		// TODO Auto-generated method stub

	}

	// d
	@Override
	public void remove(Manager manager) {
		// TODO Auto-generated method stub

	}
}
