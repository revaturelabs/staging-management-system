package com.revature.controllers;

import javax.ws.rs.FormParam;
import javax.ws.rs.QueryParam;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.revature.classes.BatchInfo;
import com.revature.classes.ClientInfo;
import com.revature.classes.DAOService;

@RestController
public class ManipulateDataController {
 
	static ApplicationContext ctx = new ClassPathXmlApplicationContext("appContext.xml");
	static DAOService daoserv = (DAOService) ctx.getBean("DAOImpl");

	@RequestMapping(value = "/getTableData", method = RequestMethod.GET)
	public List<String> getTableData()
	{
		System.out.println("hi controller");
		List<String> bang = new ArrayList<String>();
		bang.add("holy shit this actually fucking worked???");
		bang.add("oooooo");
		bang.add("aaahhhhh");
		return bang;
	}
	

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

	@RequestMapping(value = "/addAssociate")
	@ResponseBody
	public  String PostService(HttpServletRequest request) throws IOException {
		// get form data
		String name = request.getParameter("name");
		String status = request.getParameter("associatestatus");

		System.out.println(name + " :: " + status);

		// initialize an Associate object
		// AssociateInfo associate = new AssociateInfo(name, status);

		// call the addBatch method for the database
		// daoserv.addAssociate(associate);

		return "bless up";
	}

	@RequestMapping(value = "/addBatch", method = RequestMethod.POST)
	public void addBatch(HttpServletRequest req) {
		try {
			String name = req.getParameter("trainingname");
			String location = req.getParameter("location");
			String trainer = req.getParameter("trainer");
			String sd = req.getParameter("startdate");
			String ed = req.getParameter("enddate");
			String type = req.getParameter("batchtype");
			
			java.sql.Date startdate = java.sql.Date.valueOf(sd);
			java.sql.Date enddate = java.sql.Date.valueOf(ed);

			
			// initialize a batch object
			BatchInfo batch = new BatchInfo(name, location, trainer, startdate, enddate, type);
			System.out.println(batch.toString());
			
			// call the addBatch method for the database
			daoserv.AddBatch(batch);
			
			

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@RequestMapping(value = "/addClient", method = RequestMethod.POST)
	public @ResponseBody void addClient(HttpServletRequest request) {
		// get the form input
		String name = request.getParameter("clientname");
		String location = request.getParameter("location");

		// initialize a client object
		ClientInfo client = new ClientInfo(name, location);

		// call the addBatch method for the database
		daoserv.AddClient(client);
	}
}
