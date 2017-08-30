package com.revature.sms.controllers.rest;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.sms.entities.Associate;
import com.revature.sms.entities.AssociatesStatus;
import com.revature.sms.services.AssociateService;
import com.revature.sms.services.AssociatesStatusService;

@RestController
@RequestMapping("status")
public class AssociatesStatusControllerImp {
	
	@Autowired
	private AssociateService associateService;
	@Autowired
	private AssociatesStatusService associatesStatusService;
	
	private static final String LM = "login_manager";
	
	@GetMapping("/allTraining")
	public ResponseEntity<Set<Associate>> getAllActive(HttpSession session) {
		if (session.getAttribute(LM) == null) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
		}
		return ResponseEntity.ok(associateService.getAllByStatus("TRAINING"));
	}
	
	@GetMapping("/allStaging")
	public ResponseEntity<Set<Associate>> getAllInStaging(HttpSession session) {
		if (session.getAttribute(LM) == null) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
		}
		return ResponseEntity.ok(associateService.getAllByStatus("STAGING"));
	}
	
	@GetMapping("/allProject")
	public ResponseEntity<Set<Associate>> getAllInProject(HttpSession session) {
		if (session.getAttribute(LM) == null) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
		}
		return ResponseEntity.ok(associateService.getAllByStatus("PROJECT"));
	}
	
	@GetMapping("/allBench")
	public ResponseEntity<Set<Associate>> getAllInBench(HttpSession session) {
		if (session.getAttribute(LM) == null) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
		}
		return ResponseEntity.ok(associateService.getAllByStatus("BENCH"));
	}
	
	
	@PostMapping("/statusUpdate")
	public ResponseEntity<Associate> updateStatus(@RequestBody Associate associate, HttpSession session) {
		if (session.getAttribute(LM) == null) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
		}
		associatesStatusService.updateStatus(associate.getAssociateStatus());
		return ResponseEntity.ok(associate);
	}
	
	@GetMapping("/allStatusType")
    public List<AssociatesStatus> getAllStatus(HttpSession session) {
        return associatesStatusService.getAllStatusString();
    }

}
