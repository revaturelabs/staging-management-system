package com.revature.controllers.rest;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.entities.Checkin;
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
	@PostMapping
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
	@PutMapping
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

}
