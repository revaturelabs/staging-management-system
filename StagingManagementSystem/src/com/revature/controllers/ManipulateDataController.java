package com.revature.controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.FormParam;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.revature.classes.DAOService;

@RestController
public class ManipulateDataController {

	static ApplicationContext ctx = new ClassPathXmlApplicationContext("springmvc-servlet.xml");
	static DAOService daoserv = (DAOService) ctx.getBean("DAOImpl");

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@FormParam("username") String username, @FormParam("password") String password) {
		// try to get a single result from the database
		// if a single result cannot be returned from the database
		// return the user to the login page and display a notification alert
		// else, send the user to the main page
		try {
			// daoserv.login(username, password);
			return "Welcome, " + username;
		} catch (Exception e) {
			System.out.println("Error: username or password not found.");
			String msg = "Username or Password not found";
			return msg;
		}
	}

	@RequestMapping(value = "/AddAssociate", method = RequestMethod.POST)
	public @ResponseBody String PostService(HttpServletRequest request) throws IOException 
	{
		// get form data
		String name = request.getParameter("name");
		String status = request.getParameter("associatestatus");

		
		// initialize an Associate object 
		// Associate associate = new Associate(name, status);
				
		// call the addBatch method for the database 
		// daoserv.addAssociate(associate); 

		return "bless up";
	}

	@RequestMapping(value = "/addBatch", method = RequestMethod.POST)
	public @ResponseBody void addBatch(HttpServletRequest req) {
		// get the form input
		String name = req.getParameter("trainingname");
		String location = req.getParameter("location");
		String trainer = req.getParameter("trainer");
		String startDate = req.getParameter("startdate");
		String endDate = req.getParameter("enddate");
		String type = req.getParameter("batchtype");
	
		// initialize a batch object 
		// Batch batch = new Batch(name, location, trainer, startDate, endDate, type);
		
		// call the addBatch method for the database 
		// daoserv.addBatch(batch); 
		}

	
	@RequestMapping(value = "/AddClient", method = RequestMethod.POST)
	public @ResponseBody void addClient(HttpServletRequest request) 
	{
		// get the form input
		String name = request.getParameter("clientname");
		String type = request.getParameter("location");
		
		// initialize a client object 
		// Client client = new Client(name, location);
				
		// call the addBatch method for the database 
		// daoserv.addClient(client); 
	}
}