package com.revature.controllers.rest;

import java.util.Random;
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
	 * When called this will always persist a unique Client in the database.
	 * 
	 * @param Client
	 *            - Client to be persisted.
	 */
	@PostMapping
	public void addClient(@RequestBody Client Client) {
		Client.setId(0l);
		clientService.add(Client);
	}
	
  @PostMapping("/add/all")
  public void addMockClientData(@RequestBody Set<Client> client) {
    for(Client c : client)
    {
      addClient(c);
    }
  }

	/**
	 * Deletes Client with Client.id
	 * 
	 * @param Client
	 *            - holds the id to be deleted
	 */
	@DeleteMapping
	public void deleteClient(@RequestBody Client Client) {
		clientService.delete(Client);
	}

	@PostMapping("/multiple")
	public void addClients(@RequestBody Set<Client> clients) {
		clientService.addMultiple(clients);
	}

	/**
	 * If the id exists, updates information. else creates a new row with
	 * genrated id.
	 * 
	 * @param Client
	 *            - data to be persisted.
	 */
	@PutMapping
	public void updateClient(@RequestBody Client Client) {
		clientService.update(Client);
	}

	/**
	 * Gets a Client with id.
	 * 
	 * @param id
	 *            - id of Client to be retrieved.
	 * @return Client object from dataBase.
	 */
	@GetMapping("/{id}")
	public Client findById(@PathVariable long id) {
		return clientService.findById(id);
	}

	/**
	 * Gets all Clients.
	 * 
	 * @param all
	 * @return all Client objects from dataBase.
	 */
	@GetMapping("/all")
	public Set<Client> findAll() {
		return clientService.getAll();
	}

}
