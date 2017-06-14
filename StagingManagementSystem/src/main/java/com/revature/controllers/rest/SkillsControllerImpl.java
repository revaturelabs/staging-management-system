package com.revature.controllers.rest;

import com.revature.entities.Skill;
import com.revature.services.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("skill")
public class SkillsControllerImpl {

    @Autowired
    SkillService skillServ;

    public SkillsControllerImpl(SkillService skillServ) {

        super();
        this.skillServ = skillServ;
    }

    @GetMapping("all")
    public List<Skill> getAllSkills() {

        return skillServ.getAllSkills();
    }

    @PostMapping
    public Skill addSkill(@RequestBody Skill skill) {

        return skillServ.addSkill(skill);
    }
}
