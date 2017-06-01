package com.revature.services;

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
		// TODO Auto-generated constructor stub
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

}
