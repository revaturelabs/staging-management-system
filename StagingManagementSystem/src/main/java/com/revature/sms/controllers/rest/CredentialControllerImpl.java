package com.revature.sms.controllers.rest;

import com.revature.sms.entities.Credential;
import com.revature.sms.services.CredentialService;

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
@RequestMapping("credential")
public class CredentialControllerImpl {

    @Autowired
    private CredentialService credentialService;

    public CredentialControllerImpl(CredentialService credentialService) {

        super();
        this.credentialService = credentialService;
    }

    /**
     * When called this will always persist a unique credential in the database.
     *
     * @param credential - credential to be persisted.
     */
    @PostMapping
    public void addcredential(@RequestBody Credential credential) {

        credential.setId(0l);
        credentialService.add(credential);
    }

    /**
     * Deletes credential with credential.id
     *
     * @param credential - holds the id to be deleted
     */
    @DeleteMapping
    public void deletecredential(@RequestBody Credential credential) {

        credentialService.remove(credential);
    }

    /**
     * If the id exists, updates information. else creates a new row with
     * genrated id.
     *
     * @param credential - data to be persisted.
     */
    @PutMapping
    public void updatecredential(@RequestBody Credential credential) {
    	Credential old = credentialService.findById(credential.getId());
    	old.setPassword(credential.getPassword());
        credentialService.update(old);
    }

    /**
     * Gets a credential with id.
     *
     * @param id - id of credential to be retrieved.
     * @return credential object from dataBase.
     */
    //TODO: May be deprecated, test.
    @GetMapping("/{id}")
    public Credential findById(@PathVariable long id) {

        return credentialService.findById(id);
    }

    /**
     * Gets all credentials.
     *
     * @param all
     * @return all credential objects from dataBase.
     */
    @GetMapping("/all")
    public Set<Credential> findAll() {

        return credentialService.getAll();
    }
}
