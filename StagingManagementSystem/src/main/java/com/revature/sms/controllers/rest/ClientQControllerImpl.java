package com.revature.sms.controllers.rest;

import com.revature.sms.entities.ClientQuestion;
import com.revature.sms.services.ClientQService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

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
     * @param clientQ - clientQ to be persisted.
     */
    @PostMapping
    public void addclientQ(@RequestBody ClientQuestion clientQ) {

        clientQService.add(clientQ);
    }

    /**
     * Deletes clientQ with clientQ.id
     *
     * @param clientQ - holds the id to be deleted
     */
    @DeleteMapping
    public void deleteclientQ(@RequestBody ClientQuestion clientQ) {

        clientQService.delete(clientQ);
    }

    /**
     * If the id exists, updates information. else creates a new row with
     * genrated id.
     *
     * @param clientQ - data to be persisted.
     */
    @PutMapping
    public void updateclientQ(@RequestBody ClientQuestion clientQ) {

        clientQService.update(clientQ);
    }

    /**
     * Gets a clientQ with id.
     *
     * @param id - id of clientQ to be retrieved.
     * @return clientQ object from dataBase.
     */
    @GetMapping("/{id}")
    public ClientQuestion findById(@PathVariable long id) {

        return clientQService.findById(id);
    }

    /**
     * Gets all clientQs.
     *
     * @param all
     * @return all clientQ objects from dataBase.
     */
    @GetMapping("/all")
    public Set<ClientQuestion> findAll() {

        return clientQService.getAll();
    }
}
