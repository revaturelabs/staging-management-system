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

import com.revature.entities.ClientQ;
import com.revature.services.ClientQService;

@RestController
@RequestMapping("clientQ")
public class ClientQControllerImpl {

	@Autowired
	private ClientQService clientQService;

	public ClientQControllerImpl(ClientQService clientQService) {
		super();
		this.clientQService = clientQService;
	}

	/**
	 * When called this will always persist a unique clientQ in the database.
	 * 
	 * @param clientQ
	 *            - clientQ to be persisted.
	 */
	@PostMapping
	public void addclientQ(@RequestBody ClientQ clientQ) {
		clientQService.add(clientQ);
	}

	/**
	 * Deletes clientQ with clientQ.id
	 * 
	 * @param clientQ
	 *            - holds the id to be deleted
	 */
	@DeleteMapping
	public void deleteclientQ(@RequestBody ClientQ clientQ) {
		clientQService.delete(clientQ);
	}

	/**
	 * If the id exists, updates information. else creates a new row with
	 * genrated id.
	 * 
	 * @param clientQ
	 *            - data to be persisted.
	 */
	@PutMapping
	public void updateclientQ(@RequestBody ClientQ clientQ) {
		clientQService.update(clientQ);
	}

	/**
	 * Gets a clientQ with id.
	 * 
	 * @param id
	 *            - id of clientQ to be retrieved.
	 * @return clientQ object from dataBase.
	 */
	@GetMapping("/{id}")
	public ClientQ findById(@PathVariable long id) {
		return clientQService.findById(id);
	}

	/**
	 * Gets all clientQs.
	 * 
	 * @param all
	 * @return all clientQ objects from dataBase.
	 */
	@GetMapping("/all")
	public Set<ClientQ> findAll() {
		return clientQService.getAll();
	}
}
