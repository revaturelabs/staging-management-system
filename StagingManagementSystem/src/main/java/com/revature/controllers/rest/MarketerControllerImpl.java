package com.revature.controllers.rest;

import com.revature.entities.Marketer;
import com.revature.services.MarketerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Set;

@RestController("/marketer")
public class MarketerControllerImpl {
    @Autowired
    private MarketerService marketerService;

    @GetMapping("/all")
    public ResponseEntity<Set<Marketer>> getEverythingDude(){
        return ResponseEntity.ok(marketerService.getAllMarketers());
    }

    @PostMapping
    public void iPoosted(@RequestBody Marketer marketer){
        marketerService.addMarketer(marketer);
    }
}
