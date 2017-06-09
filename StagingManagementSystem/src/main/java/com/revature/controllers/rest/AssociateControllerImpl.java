package com.revature.controllers.rest;

import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import com.revature.util.DataGeneration;

@RestController
@RequestMapping("associate")
public class AssociateControllerImpl {

	@Autowired
	AssociateService associateService;
	@Autowired
	DataGeneration dataGen;

	public AssociateControllerImpl(AssociateService associateService) {
		super();
		this.associateService = associateService;
	}

	@PostMapping
	public void addAssociate(@RequestBody Associate associate) {
		associateService.add(associate);
	}
	
	 @PostMapping("/add/all")
	  public void addAssociates(@RequestBody Set<Associate> associates) {
	   for(Associate a : associates){
       associateService.add(a);       
	   }
	  }
	 
	 @GetMapping("/generate/mock-data")
	 public void generateAssociateMockDate(){
	  // dataGen.generate();
	 }

	@DeleteMapping
	public void deleteAssociate(@RequestBody Associate associate) {
		associateService.delete(associate);
	}

	@PutMapping
	public ResponseEntity<Object> updateAssociate(@RequestBody Associate associate, HttpSession session) {
		Associate authenticatedAssociate = (Associate)session.getAttribute("login_associate");
		
		if (authenticatedAssociate == null) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
		}
		
		associate.setCredential(authenticatedAssociate.getCredential());
		associateService.update(associate);
		return ResponseEntity.ok(null);
	}

	@GetMapping("/{id}")
	public Associate getAssociate(@PathVariable long id) {
		return associateService.getById(id);
	}

	@GetMapping("/all")
	public Set<Associate> findById() {
		return associateService.getAll();
	}
}
