package com.revature.services;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.entities.Associate;
import com.revature.entities.Credential;
import com.revature.entities.Manager;
import com.revature.repositories.AssociateRepo;
import com.revature.repositories.CredentialRepo;
import com.revature.repositories.ManagerRepo;

@Service
public class CredentialServiceImpl implements CredentialService {
	@Autowired
	CredentialRepo credentialRepo;
	
	@Autowired
	AssociateRepo associateRepo;
	
	@Autowired
	ManagerRepo managerRepo;

	public CredentialServiceImpl(CredentialRepo credentialRepo) {
		super();
		this.credentialRepo = credentialRepo;
	}

	@Override
	public void add(Credential credential) {
		credentialRepo.saveAndFlush(credential);
	}

	@Override
	public Set<Credential> getAll() {
		return new HashSet<>(credentialRepo.findAll());
	}

	@Override
	public Credential findById(long id) {
		return credentialRepo.getOne(id);
	}

	@Override
	public void remove(Credential credential) {
		credentialRepo.delete(credential);
	}

	@Override
	public void update(Credential credential) {
		credentialRepo.saveAndFlush(credential);
	}

	@Override
	public Object login(Credential creds) {
		Credential newCred = credentialRepo.findByUsername(creds.getUsername());
		if(newCred != null) {
			Associate associate = associateRepo.getByCredential(newCred);
			if(associate != null)
				return associate;
			else{
				Manager man =  managerRepo.getByCredential(newCred);
				return man;
			}
		}
		return null;
	}
}
