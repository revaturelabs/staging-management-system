package com.revature.controllers.rest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.entities.Associate;
import com.revature.entities.Checkin;
import com.revature.entities.Manager;
import com.revature.entities.data.CheckinReport;
import com.revature.entities.data.CheckinReport.DailyReport;
import com.revature.exceptions.AlreadyCheckedInException;
import com.revature.services.CheckinService;

@RestController
@RequestMapping("checkin")
public class CheckinControllerImpl {
    @Autowired
    private CheckinService checkinService;
    @Autowired
    private CheckinReport checkinReport;

    public CheckinControllerImpl(CheckinService checkinService) {
        this.checkinService = checkinService;
    }

    @GetMapping(path="checkin/{username}")
    public ResponseEntity<Set<Checkin>> getCheckins(@PathVariable String username, HttpSession session){
        Associate associate = (Associate) session.getAttribute("login_associate");
        if(associate != null){ // If you're not an associate..
            if(username.equals(associate.getCredential().getUsername())) // If you're asking for your own details..
                return ResponseEntity.ok(checkinService.getAllForAssociate(associate)); // You're welcome to your own data.
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null); // Otherwise, go away!
        }
        Manager manager = (Manager) session.getAttribute("login_manager");
        if(manager == null){ // And if you're not a manager..
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null); // Go away!
        }
        return ResponseEntity.ok(checkinService.getAllForAssociate(username));
    }

    @GetMapping
    public ResponseEntity<Boolean> isCheckedIn(HttpSession session){
        Associate associate = (Associate) session.getAttribute("login_associate");
        if(associate == null)
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(false);
        return ResponseEntity.ok(checkinService.hasCheckedInToday(associate));

    }

    @GetMapping("/allTodays")
    public ResponseEntity<Set<Checkin>> getTodaysCheckins(HttpSession session) {
        Manager manager = (Manager) session.getAttribute("login_manager");
        if(manager == null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
        return ResponseEntity.ok(checkinService.getTodaysCheckins());
    }


    @PutMapping
    public ResponseEntity<Boolean> checkIn(HttpSession session){
        Associate associate = (Associate) session.getAttribute("login_associate");
        if(associate == null)
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(false);
        try{
            checkinService.checkIn(associate, LocalDateTime.now());
            return ResponseEntity.ok(true);
        }catch(AlreadyCheckedInException e){
            Logger.getRootLogger().error(e);
            return ResponseEntity.status(HttpStatus.CONFLICT).body(false);
        }
    }

    /**
     * Marks a given checkin as having been verified by the currently logged in manager Accessible through POST:/checkin
     * and requires a checkin to be given.
     * @param session
     * @param checkin
     * @return
     */
    @PostMapping
    public ResponseEntity<Boolean> managerModification(HttpSession session, @RequestBody Checkin checkin){ // TODO complete and make managers be able to do checkins. Use the PUT mapping instead, for non-manager checkins.
        Manager manager = (Manager) session.getAttribute("login_manager");
        if(manager == null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(false);
        }
        if(checkin == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(false);
        checkinService.approveCheckin(manager,checkin);
        return ResponseEntity.ok(true);
    }

    @GetMapping(path="/report")
    public ResponseEntity<ArrayList<DailyReport>> getCheckins(HttpSession session){// For managers only.
        Manager manager = (Manager) session.getAttribute("login_manager");
        if(manager == null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
        return ResponseEntity.ok(checkinReport.process(checkinService.getAll()));
    }
}

