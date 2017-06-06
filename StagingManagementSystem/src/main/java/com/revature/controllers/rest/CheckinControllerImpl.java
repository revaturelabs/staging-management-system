package com.revature.controllers.rest;

import com.revature.entities.Checkin;
import com.revature.exceptions.AlreadyCheckedInException;
import com.revature.exceptions.NotLoggedInException;
import com.revature.services.CheckinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

/**
 * Created by Mykola Nikitin on 6/5/17.
 */
@RestController
@RequestMapping("checkin")
public class CheckinControllerImpl {
    @Autowired
    private CheckinService service;

    @GetMapping(path="checkin/%{username}")
    public ResponseEntity<Set<Checkin>> getCheckins(@PathVariable String username){
        //Probably verify that whoever is calling this is actually, y'know, logged in as a manager, or as the user checking things. Until that's implemented, this is wildly insecure, since anyone can call it.
        return ResponseEntity.ok(service.getAllForAssociate(username));
    }


    @GetMapping
    public ResponseEntity<Boolean> isCheckedIn(){
        try{
            return ResponseEntity.ok(service.hasCheckedInToday());
        }catch (NotLoggedInException e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(false);
        }
    }

    @PutMapping
    public ResponseEntity<Boolean> checkIn(){
        try{
            service.checkIn();
            return ResponseEntity.ok(true);
        }catch(NotLoggedInException e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(false);
        }catch(AlreadyCheckedInException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(false);
        }
    }

    @PostMapping
    public ResponseEntity<Boolean> managerCheckIn(){ // TODO complete and make managers be able to do checkins. Use the PUT mapping instead, for non-manager checkins.
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(false);
    }
}
