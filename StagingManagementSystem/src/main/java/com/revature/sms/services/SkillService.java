package com.revature.sms.services;

import java.util.List;

import com.revature.sms.entities.Skill;

public interface SkillService {

    List<Skill> getAllSkills();
    Skill addSkill(Skill skill);
}
