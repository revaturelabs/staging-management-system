package com.revature.sms.controllers.rest;

import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.sms.entities.Associate;
import com.revature.sms.services.AssociateService;
import com.revature.sms.services.AssoicateStatusService;

@RestController
@RequestMapping("status")
public class AssociateStatusControllerImp {
	
	@Autowired
	private AssociateService associateService;
	@Autowired
	private AssociateStatusService associateStatusService;
	
	@GetMapping("/allTraining")
	public ResponseEntity<Set<Associate>> getAllActive(HttpSession session) {
//		if (session.getAttribute(LM) == null) {
//			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
//		}
		return ResponseEntity.ok(associateService.getAllByStatus("TRAINING"));
	}
	
	@GetMapping("/allStaging")
	public ResponseEntity<Set<Associate>> getAllInStaging(HttpSession session) {
		return ResponseEntity.ok(associateService.getAllByStatus("STAGING"));
	}
	
	@GetMapping("/allProject")
	public ResponseEntity<Set<Associate>> getAllInProject(HttpSession session) {
		return ResponseEntity.ok(associateService.getAllByStatus("PROJECT"));
	}
	
	@GetMapping("/allBench")
	public ResponseEntity<Set<Associate>> getAllInBench(HttpSession session) {
		return ResponseEntity.ok(associateService.getAllByStatus("BENCH"));
	}
	
	@PutMapping("/statusUpdate")
	public ResponseEntity<Set<Associate>> updateStatus(@RequestBody Associate associate, HttpSession session) {
		return ResponseEntity.ok(associateStatusService.)
	}
	

}
