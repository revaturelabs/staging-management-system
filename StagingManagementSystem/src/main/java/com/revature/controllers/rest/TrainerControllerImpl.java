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

import com.revature.entities.Trainer;
import com.revature.services.TrainerService;

@RestController
@RequestMapping("trainer")
public class TrainerControllerImpl 
{
	@Autowired
	private TrainerService trainerService;
	
	public TrainerControllerImpl(TrainerService trainerService){
		super();
		this.trainerService = trainerService;
	}
	
	/**
	 * When called this will always persist a unique Trainer in the database.
	 * 
	 * @param trainer - trainer to be persisted.
	 */
	@PostMapping
	public void addTrainer(@RequestBody Trainer trainer) {
		trainer.setId(0);
		trainerService.add(trainer);
	}
	
	/**
	 * Deletes trainer with location.id
	 * 
	 * @param trainer - holds the id to be deleted
	 */
	@DeleteMapping
	public void deleteTrainer(@RequestBody Trainer trainer) {
		trainerService.delete(trainer);
	}
	
	/**
	 * If the id exists, updates information.
	 * else creates a new row with genrated id.
	 * 
	 * @param trainer - data to be persisted.
	 */
	@PutMapping
	public void updateTrainer(@RequestBody Trainer trainer) {
		trainerService.update(trainer);
	}
	
	/**
	 * Gets a trainer with id.
	 * 
	 * @param id - id of trainer to be retrieved.
	 * @return trainer object from dataBase.
	 */
	@GetMapping("/{id}")
	public Trainer findById(@PathVariable long id) {
		return trainerService.findById(id);
	}
	
	/**
	 * Gets all trainers.
	 * 
	 * @param all
	 * @return all trainer objects from dataBase.
	 */
	@GetMapping("/all")
	public List<Trainer> findAll() {
		return trainerService.getAll();
	}
}
