package com.revature.controllers.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	
	@PostMapping
	public void addAssociate(@RequestBody Associate associate) {
		System.out.println(associate);
		associateService.add(associate);
	}
	
	@DeleteMapping
	public void deleteAssociate(@RequestBody Associate associate) {
		associateService.delete(associate);
	}
	
	@PutMapping
	public void updateAssociate(@RequestBody Associate associate) {
		associateService.update(associate);
	}

	@GetMapping("/{id}")
	public Associate getAssociate(@PathVariable long id) {
		return associateService.getById(id);
	}

	@GetMapping("/all")
	public List<Associate> findById() {
		return associateService.getAll();
	}
}
