package com.revature.services;

import java.util.List;

import com.revature.entities.Skill;

public interface SkillService {

	List<Skill> getAllSkills();

	Skill addSkill(Skill skill);

}
