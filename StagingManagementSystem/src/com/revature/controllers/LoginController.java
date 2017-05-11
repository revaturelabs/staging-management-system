package com.revature.controllers;

import javax.ws.rs.FormParam;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String login(@FormParam("username") String username, @FormParam("password") String password)
	{
		// try to get a single result from the database
		// if a single result cannot be returned from the database
		// return the user to the login page and display a notification alert
		// else, send the user to the main page
		try 
		{
			// DAOImpl dao = new DAOImpl();
			// dao.login(username, password);
			return "Welcome, " + username;
		} catch(Exception e)
		{
			System.out.println("Error: username or password not found.");
			String msg = "Username or Password not found";
			return msg;
		}	
	}
	
}
