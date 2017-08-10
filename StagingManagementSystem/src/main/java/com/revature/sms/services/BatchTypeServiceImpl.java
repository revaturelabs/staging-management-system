package com.revature.sms.services;

import com.revature.sms.entities.BatchType;
import com.revature.sms.entities.Skill;
import com.revature.sms.repositories.BatchTypeRepo;
import com.revature.sms.repositories.SkillRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class BatchTypeServiceImpl implements BatchTypeService {

    @Autowired
    private BatchTypeRepo batchTypeRepo;

    @Autowired
    private SkillRepo skillRepo;

    public BatchTypeServiceImpl(BatchTypeRepo batchTypeRepo, SkillRepo skillRepo) {

        super();
        this.batchTypeRepo = batchTypeRepo;
        this.skillRepo = skillRepo;
    }

    @Override
    public BatchType add(BatchType batchType) {

        for (Skill skill : batchType.getSkills()) {
            Skill retreivedSkill = skillRepo.findFirstByValueIgnoreCase(skill.getValue());
            if (retreivedSkill != null) {
                skill.setId(retreivedSkill.getId());
            } else {
                skillRepo.saveAndFlush(skill);
            }
        }
        return batchTypeRepo.saveAndFlush(batchType);
    }

    @Override
    public Set<BatchType> getAll() {

        return new HashSet<>(batchTypeRepo.findAll());
    }

    @Override
    public BatchType findById(long id) {

        return batchTypeRepo.getOne(id);
    }

    @Override
    public void delete(BatchType batchType) {

        batchTypeRepo.delete(batchType);
    }

    @Override
    public void update(BatchType batchType) {

        batchTypeRepo.saveAndFlush(batchType);
    }
}
