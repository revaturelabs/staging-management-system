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
	public void add(Credential location) {
		credentialRepo.saveAndFlush(location);
	}

	@Override
	public Set<Credential> getAll() {
		return new HashSet<Credential>(credentialRepo.findAll());
	}

	@Override
	public Credential findById(long id) {
		return credentialRepo.getOne(id);
	}

	@Override
	public void delete(Credential location) {
		credentialRepo.delete(location);
	}

	@Override
	public void update(Credential location) {
		credentialRepo.saveAndFlush(location);
	}

	@Override
	public Object login(Credential creds) {
		Credential newCred = credentialRepo.findByUsernameAndPassword(creds.getUsername(), creds.getPassword());
		System.out.println("Here1");
		System.out.println(newCred);
		if(newCred != null) {
			System.out.println("Here2");
			Associate associate = associateRepo.getByCredential_Id(creds.getId());
			System.out.println("Here3");
			System.out.println(associate);
			if(associate != null)
				return associate;
			else{
				System.out.println("Here1");
				Manager man =  managerRepo.getByCredential_Id(creds.getId());
				System.out.println(man);
				return man;
			}
		}
		return null;
	}
}
