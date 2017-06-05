package com.revature.controllers.rest;

import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.entities.Client;
import com.revature.services.ClientService;

import java.util.List;

@RestController
@RequestMapping("client")
public class ClientControllerImpl {

	Logger log = Logger.getRootLogger();

	@Autowired
	private ClientService clientService;

	public ClientControllerImpl(ClientService clientService) {
		super();
		this.clientService = clientService;
	}

	/**
	 * When called this will always persist a unique location in the database.
	 * 
	 * @param location
	 *            - location to be persisted.
	 */
	@PostMapping
	public void addLocation(@RequestBody Client location) {
		location.setId(0);
		clientService.add(location);
	}

	/**
	 * Deletes location with location.id
	 * 
	 * @param location
	 *            - holds the id to be deleted
	 */
	@DeleteMapping
	public void deleteLocation(@RequestBody Client location) {
		clientService.delete(location);
	}

	@PostMapping("/multiple")
	public void addClients(@RequestBody Set<Client> clients) {
		System.out.println(clients);
		clientService.addMultiple(clients);
	}

	/**
	 * If the id exists, updates information. else creates a new row with
	 * genrated id.
	 * 
	 * @param location
	 *            - data to be persisted.
	 */
	@PutMapping
	public void updateLocation(@RequestBody Client location) {
		clientService.update(location);
	}

	/**
	 * Gets a location with id.
	 * 
	 * @param id
	 *            - id of location to be retrieved.
	 * @return location object from dataBase.
	 */
	@GetMapping("/{id}")
	public Client findById(@PathVariable long id) {
		return clientService.findById(id);
	}

	/**
	 * Gets all locations.
	 * 
	 * @param all
	 * @return all location objects from dataBase.
	 */
	@GetMapping("/all")
	public List<Client> findAll() {
		return clientService.getAll();
	}

}
