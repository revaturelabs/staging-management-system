package com.revature.sms.services;

import com.revature.sms.entities.Associate;
import com.revature.sms.entities.AssociatesStatus;
import com.revature.sms.entities.Skill;
import com.revature.sms.entities.StaggingAssociate;
import com.revature.sms.repositories.AssociateRepo;
import com.revature.sms.repositories.CredentialRepo;
import com.revature.sms.repositories.SkillRepo;
import com.revature.sms.repositories.StaggingAssociateRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AssociateServiceImpl implements AssociateService {

	@Autowired
	private AssociateRepo associateRepo;

	@Autowired
	private StaggingAssociateRepo staggingAssociateRepo;

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

		return associateRepo.getOne(id);
	}

	@Override
	@Transactional
	public void add(Associate associate) {

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
		if (!associate.isActive()) {
			associate.setProject(null);
		}

		final Set<Skill> skills = new LinkedHashSet<>();
		for (Skill associateSkill : associate.getSkills()) {
			Skill skill = skillRepo.findFirstByValueIgnoreCase(associateSkill.getValue());
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

		return new HashSet<>(associateRepo.findAll());
	}

	@Override
	public Set<Associate> haveNoBatch() {

		return associateRepo.findByBatchIsNull();
	}


	@Override
	public Set<Associate> findByBatchId(Long id) {

		return associateRepo.findByBatchId(id);
	}

	@Override
	public Set<Associate> findByProjectId(Long id) {

		return associateRepo.findByProjectProjectId(id);
	}

	@Override
	public Set<StaggingAssociate> getAssociatesInStaggingOn(String date) {
		return staggingAssociateRepo.getAssociatesInStaggingOn(date);
	}

	// We added this
	@Override
	public Set<Associate> getAllByStatus(String status) {
		return associateRepo.findByAssociateStatus_Status(status);
	}

	@Override
	public Set<Associate> findByAssociateStatus(String status) {
		return null;
	}

	@Override
	public Set<Associate> getAllActive() {
		Set<Associate> associates = new HashSet<Associate>();
		Set<Associate> allAssociateStaging = associateRepo.findByAssociateStatus_Status("STAGING");
		for (Associate a : allAssociateStaging) {
			associates.add(a);
		}
		Set<Associate> allAssociateBench = associateRepo.findByAssociateStatus_Status("BENCH");
		for (Associate a : allAssociateBench) {
			associates.add(a);
		}
		return associates;
	}

	@Override
	public Set<Associate> haveNoProject() {
		Set<Associate> noProject = new HashSet<Associate>();
		Set<Associate> allAssociateStaging = associateRepo.findByAssociateStatus_Status("STAGING");
		for (Associate a : allAssociateStaging) {
			if (a.getProject() == null) {
				noProject.add(a);
			}
		}
		Set<Associate> allAssociateBench = associateRepo.findByAssociateStatus_Status("BENCH");
		for (Associate a : allAssociateBench) {
			if (a.getProject() == null) {
				noProject.add(a);
			}
		}
		return noProject;
	}

	@Override
	public Set<Associate> findByNameLike(String name) {
		return associateRepo.findByNameContainingIgnoreCase(name);
	}
}
