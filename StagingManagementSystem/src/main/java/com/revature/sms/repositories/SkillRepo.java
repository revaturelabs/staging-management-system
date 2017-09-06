package com.revature.sms.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.sms.entities.Skill;

public interface SkillRepo extends JpaRepository<Skill, Long> {

    Skill findFirstByValueIgnoreCase(String value);
}
