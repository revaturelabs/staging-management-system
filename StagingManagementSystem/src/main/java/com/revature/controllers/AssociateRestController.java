package com.revature.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController("/associate")
public class AssociateRestController {

	@GetMapping("/{id}")
	public int getAssociate(@PathVariable int id) {
		return id;
	}

}
