package com.revature.controllers.rest;

import com.revature.exceptions.AlreadyCheckedInException;
import com.revature.exceptions.NotLoggedInException;
import com.revature.services.CheckinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Mykola Nikitin on 6/5/17.
 */
@RestController
@RequestMapping("checkin")
public class CheckinControllerImpl {
    @Autowired
    private CheckinService service;

    @GetMapping
    public ResponseEntity<Boolean> isCheckedIn(){
        try{
            return ResponseEntity.ok(service.hasCheckedInToday());
        }catch (NotLoggedInException e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(false);
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
