package com.revature.controllers.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.entities.Associate;
import com.revature.services.AssociateService;

@RestController
@RequestMapping("associate")
public class AssociateControllerImpl {

	@Autowired
	AssociateService associateService;

	public AssociateControllerImpl(AssociateService associateService) {
		super();
		this.associateService = associateService;
	}

	@GetMapping("/{id}")
	public Associate getAssociate(@PathVariable long id) {
		return associateService.getById(id);
	}

	@PostMapping
	public void addAssociate(@RequestBody Associate associate) {
		System.out.println(associate);
		associateService.add(associate);
	}

}
