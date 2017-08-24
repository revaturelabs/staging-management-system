package com.revature.sms.controllers.rest;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.sms.entities.Batch;
import com.revature.sms.repositories.BatchRepo;
import com.revature.sms.repositories.SalesforceRepo;
import com.revature.sms.security.models.SalesforceUser;
import com.revature.sms.services.security.SalesforceAuthorization;

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
    public void getBatches(HttpSession session)
    {
    	List<Batch> batches = sd.getRelevantBatches((SalesforceUser) session.getAttribute(SalesforceAuthorization.LM));
    	bd.save(batches);
    	
    }
}
