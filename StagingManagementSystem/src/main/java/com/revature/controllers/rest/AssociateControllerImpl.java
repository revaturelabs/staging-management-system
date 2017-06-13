package com.revature.controllers.rest;

import java.util.Collection;
import java.util.Set;

import javax.servlet.http.HttpSession;

import com.revature.entities.Manager;
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
import com.revature.entities.Manager;
import com.revature.services.AssociateService;
import com.revature.services.TotalReport;
import com.revature.services.TotalReport.TotalData;
import com.revature.util.DataGeneration;

@RestController
@RequestMapping("associate")
public class AssociateControllerImpl {

	@Autowired
	AssociateService associateService;
	@Autowired
	DataGeneration dataGen;
	@Autowired
	TotalReport totalReport;
	

	public AssociateControllerImpl(AssociateService associateService) {
		super();
		this.associateService = associateService;
	}

	@PostMapping
	public ResponseEntity addAssociate(@RequestBody Associate associate, HttpSession session) {
		if(session.getAttribute("login_manager") == null){ // If you're not logged in as a manger..
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
		}
		associateService.add(associate);
		return ResponseEntity.ok(null);
	}
	
	 @PostMapping("/add/all")
	 public ResponseEntity addAssociates(@RequestBody Set<Associate> associates, HttpSession session) {
		 if(session.getAttribute("login_manager") == null){ // If you're not logged in as a manger..
			 return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
		 }
		 for(Associate a : associates){
			 associateService.add(a);
		 }
		 return ResponseEntity.ok(null);
	 }
	 
	 @GetMapping("/generate/mock-data")
	 public void generateAssociateMockDate(){
	  dataGen.generate();
	 }

	@DeleteMapping
	public ResponseEntity deleteAssociate(@RequestBody Associate associate, HttpSession session) {
		if(session.getAttribute("login_manager") == null){ // If you're not logged in as a manger..
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
		}
	 	associateService.delete(associate);
		return ResponseEntity.ok(null);
	}

	@PutMapping
	public ResponseEntity<Object> updateAssociate(@RequestBody Associate associate, HttpSession session) {
		Associate authenticatedAssociate = (Associate)session.getAttribute("login_associate");
		Manager authenticatedManager = (Manager)session.getAttribute("login_manager");
		
		if (authenticatedAssociate != null) { // Associate edits their profile
			// Now we block any changes we don't want, by cherry picking the associate information
			// from the passed in associate into the session associate.
			authenticatedAssociate.setSkills(associate.getSkills());
			authenticatedAssociate.setPortfolioLink(associate.getPortfolioLink());
			associateService.update(authenticatedAssociate);
			return ResponseEntity.ok(null);
		}
		Manager manager = (Manager)session.getAttribute("login_manager");
		if(manager != null){// We trust managers. A lot.
			associate.setCredential(associateService.getById(associate.getId()).getCredential());
			associateService.update(associate);
			return ResponseEntity.ok(null);
		}

		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Associate> getAssociate(@PathVariable long id, HttpSession session) {
	 	Associate associate = ((Associate)session.getAttribute("login_associate"));
		if(session.getAttribute("login_manager") == null && (associate == null || associate.getId() != id)){ // If you're not logged in as a manger..
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
		}
	 	return ResponseEntity.ok(associateService.getById(id));
	}

	@GetMapping("/all")
	public ResponseEntity<Set<Associate>> getAllAssociates(HttpSession session) {
//		if(session.getAttribute("login_manager") == null){ // If you're not logged in as a manger..
//			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
//		}
		return ResponseEntity.ok(associateService.getAll());
	}
	
	@GetMapping(path="/totaldata")
	public ResponseEntity<Collection<TotalData>> getAssocaites(){
		return ResponseEntity.ok(totalReport.process(associateService.getAll()));
	}
	
}

