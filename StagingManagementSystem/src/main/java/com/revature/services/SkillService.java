package com.revature.services;

import com.revature.entities.Skill;

import java.util.List;

public interface SkillService {

    List<Skill> getAllSkills();
    Skill addSkill(Skill skill);
}
