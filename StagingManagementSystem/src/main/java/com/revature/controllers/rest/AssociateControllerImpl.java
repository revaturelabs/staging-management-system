package com.revature.controllers.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("associate")
public class AssociateControllerImpl {

	@GetMapping("/{id}")
	public int getAssociate(@PathVariable int id) {
		return id;
	}
	
	

}
