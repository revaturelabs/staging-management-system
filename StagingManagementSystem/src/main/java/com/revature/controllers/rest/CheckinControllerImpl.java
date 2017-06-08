package com.revature.controllers.rest;

import java.time.LocalDateTime;
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
import com.revature.entities.Checkin;
import com.revature.exceptions.AlreadyCheckedInException;
import com.revature.services.CheckinService;

@RestController
@RequestMapping("checkin")
public class CheckinControllerImpl 
{
	@Autowired
	private CheckinService checkinService;
	
	public CheckinControllerImpl(CheckinService checkinService){
		super();
		this.checkinService = checkinService;
	}
	
	/**
	 * When called this will always persist a unique checkin in the database.
	 * 
	 * @param checkin - checkin to be persisted.
	 */
	@PostMapping("/add")
	public void addcheckin(@RequestBody Checkin checkin) {
		checkinService.add(checkin);
	}
	
	/**
	 * Deletes checkin with location.id
	 * 
	 * @param checkin - holds the id to be deleted
	 */
	@DeleteMapping
	public void deletecheckin(@RequestBody Checkin checkin) {
	  //TODO: Mark item as removed in dataBase.
	}
	
	/**
	 * If the id exists, updates information.
	 * else creates a new row with genrated id.
	 * 
	 * @param checkin - data to be persisted.
	 */
	@PutMapping("/update")
	public void updatecheckin(@RequestBody Checkin checkin) {
		checkinService.update(checkin);
	}
	
	/**
	 * Gets a checkin with id.
	 * 
	 * @param id - id of checkin to be retrieved.
	 * @return checkin object from dataBase.
	 */
	@GetMapping("/{id}")
	public Checkin findById(@PathVariable long id) {
		return checkinService.findById(id);
	}
	
	/**
	 * Gets all checkins.
	 * 
	 * @param all
	 * @return all checkin objects from dataBase.
	 */
	@GetMapping("/all")
	public Set<Checkin> findAll() {
		return checkinService.getAll();
	}
	

	@PostMapping("/addmultiple")
	public void addcheckins(@RequestBody Set<Checkin> checkins) {
		checkinService.addcheckins(checkins);
	}

    @GetMapping(path="checkin/%{username}")
    public ResponseEntity<Set<Checkin>> getCheckins(@PathVariable String username){
        //Probably verify that whoever is calling this is actually, y'know, logged in as a manager, or as the user checking things. Until that's implemented, this is wildly insecure, since anyone can call it.
        return ResponseEntity.ok(checkinService.getAllForAssociate(username));
    }

    @GetMapping
    public ResponseEntity<Boolean> isCheckedIn(HttpSession session){
        Associate associate = (Associate) session.getAttribute("login_associate");
        if(associate == null)
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(false);

        return ResponseEntity.ok(checkinService.hasCheckedInToday(associate));
    }

    @PutMapping
    public ResponseEntity<Boolean> checkIn(HttpSession session){
        Associate associate = (Associate) session.getAttribute("login_associate");
        if(associate == null)
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(false);
        try{
          checkinService.checkIn(associate, LocalDateTime.now());
            return ResponseEntity.ok(true);
        }catch(AlreadyCheckedInException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(false);
        }
    }

    @PostMapping
    public ResponseEntity<Boolean> managerCheckIn(){ // TODO complete and make managers be able to do checkins. Use the PUT mapping instead, for non-manager checkins.
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(false);
    }
}
