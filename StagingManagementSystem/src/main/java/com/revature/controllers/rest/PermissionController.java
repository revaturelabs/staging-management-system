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

import com.revature.entities.Permission;
import com.revature.services.PermissionService;

@RestController
@RequestMapping("permission")
public class PermissionController {
	@Autowired
	private PermissionService permissionService;

	public PermissionController(PermissionService permissionService) {
		super();
		this.permissionService = permissionService;
	}

	/**
	 * When called this will always persist a unique location in the database.
	 * 
	 * @param location
	 *            - location to be persisted.
	 */
	@PostMapping
	public void addPermission(@RequestBody Permission location) {
		location.setId(0);
		permissionService.add(location);
	}

	/**
	 * Deletes location with location.id
	 * 
	 * @param location
	 *            - holds the id to be deleted
	 */
	@DeleteMapping
	public void removePermission(@RequestBody Permission location) {
		permissionService.remove(location);
	}

	/**
	 * If the id exists, updates information. else creates a new row with
	 * genrated id.
	 * 
	 * @param location
	 *            - data to be persisted.
	 */
	@PutMapping
	public void updatePermission(@RequestBody Permission location) {
		permissionService.update(location);
	}

	/**
	 * Gets a location with id.
	 * 
	 * @param id
	 *            - id of location to be retrieved.
	 * @return location object from dataBase.
	 */
	@GetMapping("/{id}")
	public Permission getById(@PathVariable long id) {
		return permissionService.getById(id);
	}

	/**
	 * Gets all locations.
	 * 
	 * @param all
	 * @return all location objects from dataBase.
	 */
	@GetMapping("/all")
	public Set<Permission> findById() {
		return permissionService.getAll();
	}
}
