package com.revature.sms.controllers.rest;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.sms.entities.Associate;
import com.revature.sms.entities.AttendanceRecord;
import com.revature.sms.entities.Checkin;
import com.revature.sms.exceptions.AlreadyCheckedInException;
import com.revature.sms.security.models.SalesforceUser;
import com.revature.sms.services.CheckinReportServiceImpl;
import com.revature.sms.services.CheckinService;

@RestController
@RequestMapping("checkin")
public class CheckinControllerImpl {
	@Autowired
	private CheckinService checkinService;

	@Autowired
	private CheckinReportServiceImpl checkinReport;

	private static final String LM = "login_manager";
	private static final String LA = "login_associate";

	public CheckinControllerImpl(CheckinService checkinService) {
		this.checkinService = checkinService;
	}

	@GetMapping
	public ResponseEntity<Boolean> isCheckedIn(HttpSession session) {
		Associate associate = (Associate) session.getAttribute(LA);
		if (associate == null)
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(false);
		return ResponseEntity.ok(checkinService.hasCheckedInToday(associate));

	}

	@GetMapping("/allTodays")
	public ResponseEntity<Set<Checkin>> getTodaysCheckins(HttpSession session) {
		SalesforceUser manager = (SalesforceUser) session.getAttribute(LM);
		if (manager == null) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
		}
		return ResponseEntity.ok(checkinService.getTodaysCheckins());
	}

	@PutMapping
	public ResponseEntity<Boolean> checkIn(HttpSession session) {
		Associate associate = (Associate) session.getAttribute(LA);
		if (associate == null)
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(false);
		try {
			checkinService.checkIn(associate, LocalDateTime.now());
			return ResponseEntity.ok(true);
		} catch (AlreadyCheckedInException e) {
			Logger.getRootLogger().error(e);
			return ResponseEntity.status(HttpStatus.CONFLICT).body(false);
		}
	}

/*	*//**
	 * Marks a given checkin as having been verified by the currently logged in
	 * manager Accessible through POST:/checkin and requires a checkin to be
	 * given.
	 *
	 * @param session
	 * @param checkin
	 * @return
	 */
	@PostMapping //TODO: Fix checkin, then this can be used
	public ResponseEntity<Boolean> managerModification(HttpSession session, @RequestBody Checkin checkin) {
		SalesforceUser manager = (SalesforceUser) session.getAttribute(LM);
		if (manager == null) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(false);
		}
		if (checkin == null)
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(false);
		checkinService.approveCheckin(checkin);
		return ResponseEntity.ok(true);
	}

	@PatchMapping("approve-multiple")
	public ResponseEntity<Boolean> approveMultiple(HttpSession session, @RequestBody Set<Checkin> checkins) {
		SalesforceUser manager = (SalesforceUser) session.getAttribute(LM);
		if (manager == null) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(false);
		}
		if (checkins == null)
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(false);

		checkinService.approveMultiple(checkins);
		return ResponseEntity.ok(true);
	}

	@GetMapping(path = "checkin/{username}")
	public ResponseEntity<Set<Checkin>> getCheckins(@PathVariable String username, HttpSession session) {
		Associate associate = (Associate) session.getAttribute(LA);
		if (associate != null) { // If you're not an associate..
			if (username.equals(associate.getCredential().getUsername())) // If you're asking for your own details..
				return ResponseEntity.ok(checkinService.getAllForAssociate(associate)); // You're welcome to
																						// your own data.
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null); // Otherwise, go
																			// away!
		}
		SalesforceUser manager = (SalesforceUser) session.getAttribute(LM);
		if (manager == null) { // And if you're not a manager..
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null); // Gos away!
		}
		return ResponseEntity.ok(checkinService.getAllForAssociate(username));
	}

	@GetMapping(path = "/report") //TODO: Update after checkin is functional
	public ResponseEntity<Collection<AttendanceRecord>> getCheckins(HttpSession session) {// For managers only.
//		Manager manager = (Manager) session.getAttribute(LM);
//		if (manager == null) {
//			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
//		}
		//return ResponseEntity.ok(checkinReport.process(checkinService.getAll()));
    return ResponseEntity.ok(checkinReport.getReport());
	}
}
