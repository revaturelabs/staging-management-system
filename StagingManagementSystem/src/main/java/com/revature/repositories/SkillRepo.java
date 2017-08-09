package com.revature.repositories;

import com.revature.entities.Skill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SkillRepo extends JpaRepository<Skill, Long> {

    Skill findFirstByValueIgnoreCase(String value);
}
