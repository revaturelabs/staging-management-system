package com.revature.controllers.rest;

import com.revature.entities.Marketer;
import com.revature.services.MarketerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/marketer")
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
