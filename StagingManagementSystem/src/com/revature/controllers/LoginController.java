package com.revature.controllers;

import javax.ws.rs.FormParam;
import javax.ws.rs.Path;

public class LoginController {

	@Path("/login")
	public String login(@FormParam("") String username, @FormParam("") String password)
	{
		
		return "Welcome, " + username + ", " + password;
	}
	
}
