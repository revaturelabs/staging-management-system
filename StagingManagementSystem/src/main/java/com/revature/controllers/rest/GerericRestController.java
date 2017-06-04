package com.revature.controllers.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.revature.entities.Permission;
import com.revature.entities.SmsEntities;
import com.revature.services.GenericService;

public class GerericRestController <V extends SmsEntities, T extends GenericService<V>>
{
	@Autowired
	private T service;
	
	public GerericRestController(T service)
	{
		super();
		this.service = service;
	}
	
	/**
	 * When called this will always persist as a unique column in the database.
	 * 
	 * @param location - location to be persisted.
	 */
	@PostMapping
	public void addPermission(@RequestBody V obj) {
		obj.setId(0);
		service.add(obj);
	}
	
	/**
	 * Deletes location with location.id
	 * 
	 * @param location - holds the id to be deleted
	 */
	@DeleteMapping
	public void deletePermission(@RequestBody V obj) {
		service.delete(obj);
	}
	
	/**
	 * If the id exists, updates information.
	 * else creates a new row with genrated id.
	 * 
	 * @param location - data to be persisted.
	 */
	@PutMapping
	public void updatePermission(@RequestBody V obj) {
		service.update(obj);
	}

	/**
	 * Gets a location with id.
	 * 
	 * @param id - id of location to be retrieved.
	 * @return location object from dataBase.
	 */
	@GetMapping("/{id}")
	public V findById(@PathVariable long id) {
		return service.findById(id);
	}
	
	/**
	 * Gets all locations.
	 * 
	 * @param all
	 * @return all location objects from dataBase.
	 */
	@GetMapping("/all")
	public List<V> findById() {
		return service.getAll();
	}
}
