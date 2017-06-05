package com.revature.services;

import java.util.HashSet;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.entities.Associate;
import com.revature.repositories.AssociateRepo;
import com.revature.repositories.CredentialRepo;

@Service
public class AssociateServiceImpl implements AssociateService {

	@Autowired
	private AssociateRepo associateRepo;

	@Autowired
	private CredentialRepo credentialRepo;

	public AssociateServiceImpl(AssociateRepo associateRepo, CredentialRepo credentialRepo) {
		super();
		this.associateRepo = associateRepo;
		this.credentialRepo = credentialRepo;
	}

	public AssociateServiceImpl() {
		super();
	}

	@Override
	public Associate getById(long id) {
		Associate associate = associateRepo.getOne(id);
		System.out.println("");
		System.out.println("");
		System.out.println(associate);
		return associate;
	}

	@Override
	@Transactional
	public void add(Associate associate) {
		System.out.println(associate);
		credentialRepo.save(associate.getCredential());
		associateRepo.saveAndFlush(associate);
	}

	@Override
	public void delete(Associate associate) {
		associateRepo.delete(associate);
		credentialRepo.delete(associate.getCredential());
	}

	@Override
	public void update(Associate associate) {
		associateRepo.saveAndFlush(associate);
		credentialRepo.save(associate.getCredential());
	}

	@Override
	public Set<Associate> getAll() {
		return new HashSet<Associate>(associateRepo.findAll());
	}
}
