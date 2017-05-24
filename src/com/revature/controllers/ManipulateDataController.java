package com.revature.controllers;

import javax.ws.rs.FormParam;
import javax.ws.rs.Produces;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.gson.Gson;
import com.revature.classes.AssociateInfo;
import com.revature.classes.BatchInfo;
import com.revature.classes.ClientInfo;
import com.revature.classes.DAOImpl;
import com.revature.classes.DAOService;
import com.revature.classes.Week;

@RestController
public class ManipulateDataController {

	final Logger logger = Logger.getLogger( DAOImpl.class );
	
	ApplicationContext ctx = new ClassPathXmlApplicationContext("appContext.xml");
	DAOService daoserv = (DAOService) ctx.getBean("DAOImpl");

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView displayLogin(@RequestParam(value = "error", required = false) String error,
		@RequestParam(value = "logout", required = false) String logout) {
		ModelAndView model = new ModelAndView();
		if (error != null) {
			model.addObject("error", "Invalid username and password!");
		}

		if (logout != null) {
			model.addObject("msg", "You've been logged out successfully.");
		}
		model.setViewName("login");

		return model;
	}

	@RequestMapping(value = "/checkLogin", method = RequestMethod.POST)
	public String CheckLogin(@FormParam("username") String username, @FormParam("password") String password) {
		// try to get a single result from the database
		// if a single result cannot be returned from the database
		// return the user to the login page and display a notification alert
		// else, send the user to the main page
		try {
				// Log4J
				logger.info( "Successful login with user" + username );
				
				// daoserv.login(username, password);
				return "Welcome, " + username;
			
		} catch (Exception e) {
			System.out.println("Error: username or password not found.");
			String msg = "Username or Password not found";
			
			// Log4J
			logger.info( "Unsuccessful login with user" + username );
			return msg;
		}
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public ModelAndView logout(HttpServletRequest req) {
		HttpSession session = req.getSession();
		session.invalidate();

		ModelAndView model = new ModelAndView();

		model.setViewName("login");

		return model;
	}

	@RequestMapping(value = "/addAssociate", method = RequestMethod.POST)
	public void PostService(HttpServletRequest request) throws IOException {
		// get form data
		String name = request.getParameter("name");
		String status = request.getParameter("associatestatus");
		String b = request.getParameter("batch");
		BatchInfo batch = new BatchInfo();
		batch.setTrainingName(b);
		Set<BatchInfo> batches = new HashSet<BatchInfo>();
		batches.add(batch);

		System.out.println(name + " :: " + status + " :: " + batch);

		// initialize an Associate object
		AssociateInfo associate = new AssociateInfo(name, status, batch);

		// call the addBatch method for the database
		daoserv.AddAssociate(associate);
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

			// call the addBatch method for the database
			daoserv.AddBatch(batch);
			
			// Log4J
			logger.info( "Batch " + name + " added successfully" );
		
		}  catch (Exception e) {
			e.printStackTrace();
			
			// Log4J
			logger.info( "Unsuccessful attempt to add new batch" );
		}
	}

	@RequestMapping(value = "/addClient", method = RequestMethod.POST)
	public void addClient(HttpServletRequest request) {
	try{
			// get the form input
			String name = request.getParameter("clientname");
			String location = request.getParameter("location");
	
			// initialize a client object
			ClientInfo client = new ClientInfo(name, location);
	
			// call the addBatch method for the database
			daoserv.AddClient(client);
			
			// Log4J
			logger.info( "Client " + name + " added successfully" );
		}
	
	catch( Exception e )
	{
		// Log4J
		logger.info( "Unsuccessful attempt to add new client" );
	}
}
	@RequestMapping(value = "/displayStats", method = RequestMethod.POST)
	public @ResponseBody ArrayList<AssociateInfo> ViewAssociateStats(HttpServletRequest req)
			throws JsonGenerationException, JsonMappingException, IOException {
		ArrayList<AssociateInfo> associates = daoserv.GetAllAssociatesDB();
		return associates;
	}

	@RequestMapping(value = "/displayClients", method = RequestMethod.GET)
	public @ResponseBody ArrayList<ClientInfo> ViewClients(HttpServletRequest req)
			throws JsonGenerationException, JsonMappingException, IOException {
		ArrayList<ClientInfo> client = daoserv.GetAllClientsDB();
		return client;
	}

	@RequestMapping(value = "/displayBatch", method = RequestMethod.GET)
	public @ResponseBody ArrayList<BatchInfo> ViewBatch(HttpServletRequest req, HttpServletResponse resp)
			throws JsonGenerationException, JsonMappingException, IOException {
		ArrayList<BatchInfo> batch = daoserv.GetAllBatchesDB();
		return batch;

	}

	// -------------------------------------------------------------------------------- //
	// ------------------------------- JAVA 
	// -------------------------------------------------------------------------------- //

	// Mapped Java Number
	@RequestMapping(value = "/displayCurrentJavaMapped", method = RequestMethod.GET)
	public @ResponseBody int displayCurrentJavaMapped() {
		int mappedJava = daoserv.getMappedCurrentJava().size();
		
		// Log4J
		logger.info( "Mapped Java Count: "+ mappedJava );
						
		return mappedJava;

	}

	// Mapped Java List
	@RequestMapping(value = "/displayCurrentJavaMappedList", method = RequestMethod.GET)
	public @ResponseBody List displayCurrentJavaMappedList() {
		List mappedJavaList = daoserv.getMappedCurrentJava();
		
		// Log4J
		logger.info( "Mapped Java list: "+ mappedJavaList );
						
		return mappedJavaList;

	}

	// Confirmed Java Number
	@RequestMapping(value = "/displayCurrentJavaConfirmed", method = RequestMethod.GET)
	public @ResponseBody int displayCurrentJavaConfirmed() {
		int confirmedJava = daoserv.getConfirmedCurrentJava().size();
		
		// Log4J
		logger.info( "Confirmed Java Count: "+ confirmedJava );
						
		return confirmedJava;

	}

	// Confirmed Java List
	@RequestMapping(value = "/displayCurrentJavaConfirmedList", method = RequestMethod.GET)
	public @ResponseBody List displayCurrentJavaConfirmedList() {
		List confirmedJavaList = daoserv.getConfirmedCurrentJava();
		
		// Log4J
		logger.info( "Confirmed Java list: "+ confirmedJavaList );
						
		return confirmedJavaList;

	}

	// Available Java Number
	@RequestMapping(value = "/displayCurrentJavaAvailable", method = RequestMethod.GET)
	public @ResponseBody int displayCurrentJavaAvailable() {
		int availableJava = daoserv.getAvailableCurrentJava().size();
		
		// Log4J
		logger.info( "Available Java Count: "+ availableJava );
						
		return availableJava;

	}

	// Available Java List
	@RequestMapping(value = "/displayCurrentJavaAvailableList", method = RequestMethod.GET)
	public @ResponseBody List displayCurrentJavaAvailableList() {
		List availableJavaList = daoserv.getAvailableCurrentJava();
		
		// Log4J
		logger.info( "Available Java list: "+ availableJavaList );
						
		return availableJavaList;

	}

	// --------------------------------------------------------------------------------//
	// ------------------------------- SDET
	// --------------------------------------------------------------------------------//

	// Mapped SDET Number
	@RequestMapping(value = "/displayCurrentSDETMapped", method = RequestMethod.GET)
	public @ResponseBody int displayCurrentSDETMapped() {
		int mappedSDET = daoserv.getMappedCurrentSDET().size();
		
		// Log4J
		logger.info( "Mapped SDET Count: "+ mappedSDET );
						
		return mappedSDET;
	}

	// Mapped SDET List
	@RequestMapping(value = "/displayCurrentSDETMappedList", method = RequestMethod.GET)
	public @ResponseBody List displayCurrentSDETMappedList() {
		List mappedSDETList = daoserv.getMappedCurrentSDET();
		
		// Log4J
		logger.info( "Mapped SDET list: "+ mappedSDETList );
						
		return mappedSDETList;
	}

	// Confirmed SDET Number
	@RequestMapping(value = "/displayCurrentSDETConfirmed", method = RequestMethod.GET)
	public @ResponseBody int displayCurrentSDETConfirmed() {
		int confirmedSDET = daoserv.getConfirmedCurrentSDET().size();
		
		// Log4J
		logger.info( "Confirmed SDET Count: "+ confirmedSDET );
						
		return confirmedSDET;
	}

	// Confirmed SDET List
	@RequestMapping(value = "/displayCurrentSDETConfirmedList", method = RequestMethod.GET)
	public @ResponseBody List displayCurrentSDETConfirmedList() {
		List confirmedSDETList = daoserv.getConfirmedCurrentSDET();
		
		// Log4J
		logger.info( "Confirmed Java list: "+ confirmedSDETList );
						
		return confirmedSDETList;
	}

	// Available SDET Number
	@RequestMapping(value = "/displayCurrentSDETAvailable", method = RequestMethod.GET)
	public @ResponseBody int displayCurrentSDETAvailable() {
		int availableSDET = daoserv.getAvailableCurrentSDET().size();
		
		// Log4J
		logger.info( "Available SDET Count: "+ availableSDET );
						
		return availableSDET;
	}

	// Available SDET List
	@RequestMapping(value = "/displayCurrentSDETAvailableList", method = RequestMethod.GET)
	public @ResponseBody List displayCurrentSDETAvailableList() {
		List availableSDETList = daoserv.getAvailableCurrentSDET();
		
		// Log4J
		logger.info( "Confirmed SDET list: "+ availableSDETList );
						
		return availableSDETList;
	}

	// --------------------------------------------------------------------------------//
	// ------------------------------- .NET
	// --------------------------------------------------------------------------------//

	// Mapped NET Number
	@RequestMapping(value = "/displayCurrentNETMapped", method = RequestMethod.GET)
	public @ResponseBody int displayCurrentNETMapped() {
		int mappedNET = daoserv.getMappedCurrentNET().size();
		
		// Log4J
		logger.info( "Mapped .NET Count: "+ mappedNET );
						
		return mappedNET;
	}

	// Mapped NET List
	@RequestMapping(value = "/displayCurrentNETMappedList", method = RequestMethod.GET)
	public @ResponseBody List displayCurrentNETMappedList() {
		List mappedNETList = daoserv.getMappedCurrentNET();
		
		// Log4J
		logger.info( "Mapped .NET list: "+ mappedNETList );
						
		return mappedNETList;
	}

	// Confirmed NET Number
	@RequestMapping(value = "/displayCurrentNETConfirmed", method = RequestMethod.GET)
	public @ResponseBody int displayCurrentNETConfirmed() {
		int confirmedNET = daoserv.getConfirmedCurrentNET().size();
		
		// Log4J
		logger.info( "Confirmed .NET Count: "+ confirmedNET );
						
		return confirmedNET;
	}

	// Confirmed NET Number
	@RequestMapping(value = "/displayCurrentNETConfirmedList", method = RequestMethod.GET)
	public @ResponseBody List displayCurrentNETConfirmedList() {
		List confirmedNETList = daoserv.getConfirmedCurrentNET();
		
		// Log4J
		logger.info( "Confirmed .NET list: "+ confirmedNETList );
						
		return confirmedNETList;
	}

	// Available NET Number
	@RequestMapping(value = "/displayCurrentNETAvailable", method = RequestMethod.GET)
	public @ResponseBody int displayCurrentNETAvailable() {
		int availableNET = daoserv.getAvailableCurrentNET().size();
		
		// Log4J
		logger.info( "Available .NET Count: "+ availableNET );
						
		return availableNET;
	}

	// Available NET Number
	@RequestMapping(value = "/displayCurrentNETAvailableList", method = RequestMethod.GET)
	public @ResponseBody List displayCurrentNETAvailableList() {
		List availableNETList = daoserv.getAvailableCurrentNET();
		
		// Log4J
		logger.info( "Available .NET list: "+ availableNETList );
						
		return availableNETList;
	}
	
	
//--------------------------------------  END BRANDON'S BLOCK  ------------------------------------------//
	
	
	@RequestMapping(value="/updateAssociates" , method = RequestMethod.POST, consumes ={"application/JSON"})
	public void updateAssociates( @RequestBody String status) {

		try
		{
			// Log4J
			logger.info( "Updating!!!!!!!!!!!!!!!!!!!!" + status.toString( ) );
		}
		
		catch( Exception e )
		{
			// Log4J
			logger.error( "Updating status " + status.toString( ) + "has errored" );
		}
	}
}
