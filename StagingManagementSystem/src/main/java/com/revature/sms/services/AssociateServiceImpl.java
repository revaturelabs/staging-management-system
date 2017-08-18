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

//	  We deleted this
//    @Override
//    public Set<Associate> getAllActive() {
//
//        return associateRepo.findAssociatesByActiveTrue();
//    }
    

    @Override
    public Set<Associate> haveNoBatch() {

        return associateRepo.findByBatchIsNull();
    }

    @Override
    public Set<Associate> findByBatchId(Long id) {

        return associateRepo.findByBatchId(id);
    }

    @Override
    public Set<StaggingAssociate> getAssociatesInStaggingOn(String date) {
      return staggingAssociateRepo.getAssociatesInStaggingOn(date);
    }

    //We added this
	@Override
	public Set<Associate> getAllByStatus(AssociatesStatus status) {
		return associateRepo.findByAssociateStatus(status);
	}

//	public Set<Associate> getActive() {
//		return associateRepo.findAssociateByStatusIsStagingOrBench();
//	}

}
