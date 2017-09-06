package com.revature.sms.services;

import com.revature.sms.entities.Skill;
import com.revature.sms.repositories.SkillRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkillServiceImpl implements SkillService {

    @Autowired
    SkillRepo skillRepo;

    public SkillServiceImpl(SkillRepo skillRepo) {

        super();
        this.skillRepo = skillRepo;
    }

    @Override
    public List<Skill> getAllSkills() {

        return skillRepo.findAll();
    }

    @Override
    public Skill addSkill(Skill skill) {

        return skillRepo.saveAndFlush(skill);
    }
}
