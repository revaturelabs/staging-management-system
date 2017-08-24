package com.revature.sms.controllers.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.sms.entities.Batch;
import com.revature.sms.repositories.BatchRepo;
import com.revature.sms.repositories.SalesforceRepo;

@RestController
@RequestMapping("test")
public class TestImpl {

	@Autowired
	private SalesforceRepo sd;
	
	@Autowired
	private BatchRepo bd;
	
    @GetMapping("/testone")
    public void permissionTest() {
    }
    
    @GetMapping("/batches")
    public void getBatches()
    {
    	List<Batch> batches = sd.getRelevantBatches();
    	bd.save(batches);
    	
    }
}
