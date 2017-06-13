package com.revature.services;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.entities.Associate;
import com.revature.entities.Credential;
import com.revature.entities.Skill;
import com.revature.repositories.AssociateRepo;
import com.revature.repositories.CredentialRepo;
import com.revature.repositories.SkillRepo;

@Service
public class AssociateServiceImpl implements AssociateService {

	@Autowired
	private AssociateRepo associateRepo;

	@Autowired
	private CredentialRepo credentialRepo;
	
	@Autowired
	private SkillRepo skillRepo;

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
		return associate;
	}

	@Override
	@Transactional
	public void add(Associate associate) {
		credentialRepo.save(associate.getCredential());
		associate = associateRepo.saveAndFlush(associate);
	}

	@Override
	public void delete(Associate associate) {
//		if(associate == null){
//			throw new NullReferenceException("Manager is null.");
//		}
		Credential credential = associate.getCredential();
		
//		if(credential == null){
//			throw new NullReferenceException("Credential is null.");
//		}
		associateRepo.delete(associate);
		credentialRepo.delete(associate.getCredential());
	}

	@Override
	public void update(Associate associate) {
		final Set<Skill> skills = new LinkedHashSet<>();
		for (Skill associateSkill : associate.getSkills()) {
			Skill skill = skillRepo.findFirstByValue(associateSkill.getValue());
			if (skill != null) {
				skills.add(skill);
			} else {
				skills.add(skillRepo.saveAndFlush(associateSkill));
			}
		}
		associate.setSkills(skills);
		associateRepo.saveAndFlush(associate);
		credentialRepo.save(associate.getCredential());
	}

	@Override
	public Set<Associate> getAll() {
		return new HashSet<Associate>(associateRepo.findAll());
	}
}
