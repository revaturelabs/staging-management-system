package com.revature.controllers.rest;

import com.revature.entities.Permission;
import com.revature.exceptions.SmsCustomException;
import com.revature.services.PermissionService;
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
@RequestMapping("permission")
public class PermissionControllerImpl {

    @Autowired
    private PermissionService permissionService;

    public PermissionControllerImpl(PermissionService permissionService) {

        super();
        this.permissionService = permissionService;
    }

    /**
     * When called this will always persist a unique permission in the database.
     *
     * @param permission - permission to be persisted.
     * @throws SmsCustomException
     */
    @PostMapping
    public void addPermission(@RequestBody Permission permission) throws SmsCustomException {

        permission.setId((long) 0);
        permissionService.add(permission);
    }

    /**
     * When called this will always persist a unique permission in the database.
     *
     * @param permission - permission to be persisted.
     * @throws SmsCustomException
     */
    @PostMapping("/add/all")
    public void addPermission(@RequestBody Set<Permission> permissions) throws SmsCustomException {

        for (Permission p : permissions)
            permissionService.add(p);
    }

    /**
     * Deletes permission with permission.id
     *
     * @param permission - holds the id to be deleted
     * @throws SmsCustomException
     */
    @DeleteMapping
    public void removePermission(@RequestBody Permission permission) throws SmsCustomException {

        permissionService.remove(permission);
    }

    /**
     * If the id exists, updates information. else creates a new row with
     * genrated id.
     *
     * @param permission - data to be persisted.
     * @throws SmsCustomException
     */
    @PutMapping
    public void updatePermission(@RequestBody Permission permission) throws SmsCustomException {

        permissionService.update(permission);
    }

    /**
     * Gets a permission with id.
     *
     * @param id - id of location to be retrieved.
     * @return location object from dataBase.
     * @throws SmsCustomException
     */
    @GetMapping("/{id}")
    public Permission getById(@PathVariable long id) throws SmsCustomException {

        return permissionService.getById(id);
    }

    /**
     * Gets all permissions.
     *
     * @param all
     * @return all permission objects from dataBase.
     */
    @GetMapping("/all")
    public Set<Permission> findById() {

        return permissionService.getAll();
    }
}
