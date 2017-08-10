package com.revature.sms.controllers.rest;

import com.revature.sms.entities.Manager;
import com.revature.sms.exceptions.SmsCustomException;
import com.revature.sms.services.ManagerService;

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
@RequestMapping("manager")
public class ManagerControllerImpl {

    @Autowired
    private ManagerService managerService;

    public ManagerControllerImpl(ManagerService managerService) {

        super();
        this.managerService = managerService;
    }

    /**
     * When called this will always persist a unique manager in the database.
     *
     * @param manager - manager to be persisted.
     * @throws SmsCustomException
     */
    @PostMapping
    public void addManager(@RequestBody Manager manager) throws SmsCustomException {

        manager.setId((long) 0);
        managerService.add(manager);
    }

    /**
     * Add a list manager in-efficient function intended for mock data.
     *
     * @param manager - manager to be persisted.
     * @throws SmsCustomException
     */
    @PostMapping("/add/all")
    public void addManagers(@RequestBody Set<Manager> managers) throws SmsCustomException {

        for (Manager m : managers)
            managerService.add(m);
    }

    /**
     * Deletes manager with location.id
     *
     * @param manager - holds the id to be deleted
     * @throws SmsCustomException
     */
    @DeleteMapping
    public void removeManager(@RequestBody Manager manager) throws SmsCustomException {

        managerService.remove(manager);
    }

    /**
     * If the id exists, updates information. else creates a new row with
     * genrated id.
     *
     * @param manager - data to be persisted.
     * @throws SmsCustomException
     */
    @PutMapping
    public void updateManager(@RequestBody Manager manager) throws SmsCustomException {

        managerService.update(manager);
    }

    /**
     * Gets a manager with id.
     *
     * @param id - id of manager to be retrieved.
     * @return manager object from dataBase.
     * @throws SmsCustomException
     */
    @GetMapping("/{id}")
    public Manager getById(@PathVariable long id) throws SmsCustomException {

        return managerService.getById(id);
    }

    /**
     * Gets all managers.
     *
     * @param all
     * @return all manager objects from dataBase.
     */
    @GetMapping("/all")
    public Set<Manager> findAll() {

        return managerService.getAll();
    }
}
