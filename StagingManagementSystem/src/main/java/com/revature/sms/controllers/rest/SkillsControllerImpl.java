package com.revature.sms.controllers.rest;

import com.revature.sms.entities.Skill;
import com.revature.sms.services.SkillService;

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
