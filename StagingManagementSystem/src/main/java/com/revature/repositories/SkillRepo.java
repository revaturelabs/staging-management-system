package com.revature.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.entities.Credential;
import com.revature.entities.Skill;

public interface SkillRepo extends JpaRepository<Skill, Long> {
	Skill findFirstByValueIgnoreCase(String value);
}
