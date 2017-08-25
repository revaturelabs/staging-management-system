package com.revature.sms.controllers.rest;

import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.sms.entities.Associate;
import com.revature.sms.services.AssociateService;

@RestController
@RequestMapping("associateStatus")
public class AssociateStatusControllerImp {
	
	@Autowired
	private AssociateService associateService;

}
