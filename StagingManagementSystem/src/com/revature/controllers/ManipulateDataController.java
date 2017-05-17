package com.revature.controllers;

import javax.ws.rs.FormParam;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.revature.classes.AssociateInfo;
import com.revature.classes.BatchInfo;
import com.revature.classes.ClientInfo;
import com.revature.classes.DAOService;

@RestController
public class ManipulateDataController {

	ApplicationContext ctx = new ClassPathXmlApplicationContext("appContext.xml");
	DAOService daoserv = (DAOService) ctx.getBean("DAOImpl");
	
	@RequestMapping(value = "/getTableData", method = RequestMethod.POST)
	public @ResponseBody String getTableData()
	{
		return "holy shit this actually fucking worked???";
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

	@RequestMapping(value = "/addAssociate", method = RequestMethod.POST)
	public void PostService(HttpServletRequest request) throws IOException {
		// get form data
		String name = request.getParameter("name");
		String status = request.getParameter("associatestatus");
		String client = request.getParameter("client");
		ClientInfo clientInfo = new ClientInfo();
		clientInfo.setName(client);
		Set<ClientInfo> clients = null;
		clients.add(clientInfo);

		System.out.println(name + " :: " + status + " :: " + client);

		// initialize an Associate object
		AssociateInfo associate = new AssociateInfo(name, status, clients);

		// call the addBatch method for the database
		// daoserv.addAssociate(associate);
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
	public void addClient(HttpServletRequest request) {
		// get the form input
		String name = request.getParameter("clientname");
		String location = request.getParameter("location");

		// initialize a client object
		ClientInfo client = new ClientInfo(name, location);

		// call the addBatch method for the database
		daoserv.AddClient(client);
	}
	
	@RequestMapping(value = "/displayStats", method = RequestMethod.POST)
	public @ResponseBody String ViewAssociateStats(HttpServletRequest req) throws JsonGenerationException, JsonMappingException, IOException
	{
		List<AssociateInfo> associates;
		
		associates = daoserv.GetAllAssociatesDB();
		

    	ObjectMapper objectMapper = new ObjectMapper();
    	objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

		String arrayToJson = objectMapper.writeValueAsString(associates);
    	System.out.println("1. Convert Array to JSON :");
    	System.out.println(arrayToJson);
    	
    	return arrayToJson;
	}
	
	@RequestMapping(value = "/displayClients", method = RequestMethod.POST)
	public @ResponseBody String ViewClients(HttpServletRequest req) throws JsonGenerationException, JsonMappingException, IOException
	{
		List<ClientInfo> clients;
		
		clients = daoserv.GetAllClientsDB();
		

    	ObjectMapper objectMapper = new ObjectMapper();
    	objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

		String arrayToJson = objectMapper.writeValueAsString(clients);
    	System.out.println("1. Convert Array to JSON :");
    	System.out.println(arrayToJson);
    	
    	return arrayToJson;
	}
	
	@RequestMapping(value = "/displayBatch", method = RequestMethod.POST)
	public @ResponseBody String ViewBatch(HttpServletRequest req) throws JsonGenerationException, JsonMappingException, IOException
	{
		List<BatchInfo> batch;
		
		batch = daoserv.GetAllBatchesDB();
		

    	ObjectMapper objectMapper = new ObjectMapper();
    	objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

		String arrayToJson = objectMapper.writeValueAsString(batch);
    	System.out.println("1. Convert Array to JSON :");
    	System.out.println(arrayToJson);
    	
    	return arrayToJson;
	}

}
