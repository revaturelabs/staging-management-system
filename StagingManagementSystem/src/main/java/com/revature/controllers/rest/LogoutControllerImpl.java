package com.revature.controllers.rest;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("logout")
public class LogoutControllerImpl {
	
	@GetMapping
	public void getLogout(HttpServletRequest req) {
		if(req.getSession() != null)
		{
			req.getSession().invalidate();
		}
	}
}