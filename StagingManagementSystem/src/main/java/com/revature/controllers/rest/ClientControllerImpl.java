package com.revature.controllers.rest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
	
	@PostMapping
	public void addClient(@RequestBody Client client) {
		clientService.add(client);
	}
	
	@GetMapping("/{id}") 
	public Client getClient(@PathVariable long id) {
		return clientService.getClientbyId(id);
	}

	@GetMapping("/all")
	public List<Client> getAll() {return clientService.getAll();}
	
}
