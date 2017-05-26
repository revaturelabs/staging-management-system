package com.revature.controllers;

import javax.ws.rs.FormParam;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
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
import com.revature.classes.AvailableAssociate;
import com.revature.classes.BatchInfo;
import com.revature.classes.ClientInfo;
import com.revature.classes.ConfirmedAssociate;
import com.revature.classes.DAOImpl;
import com.revature.classes.DAOService;
import com.revature.classes.MappedAssociate;
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


	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public ModelAndView logout(HttpServletRequest req)
	{
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

		logger.info(name + " :: " + status + " :: " + batch);

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
			logger.info(batch.toString());
			
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
	
	// not sure if actually using
	@RequestMapping(value = "/displayStats", method = RequestMethod.GET)
	public @ResponseBody ArrayList<AssociateInfo> ViewAssociateStats(HttpServletRequest req) throws JsonGenerationException, JsonMappingException, IOException
	{
		ArrayList<AssociateInfo> associates = daoserv.GetAllAssociatesDB();
    	return associates;
	}
	
	@RequestMapping(value = "/displayClients", method = RequestMethod.GET)
	public @ResponseBody ArrayList<ClientInfo> ViewClients(HttpServletRequest req) throws JsonGenerationException, JsonMappingException, IOException
	{
		ArrayList<ClientInfo> client = daoserv.GetAllClientsDB();
    	return client;
	}
	
	@RequestMapping(value = "/displayBatch", method = RequestMethod.GET)
	public @ResponseBody ArrayList<BatchInfo> ViewBatch(HttpServletRequest req, HttpServletResponse resp) throws JsonGenerationException, JsonMappingException, IOException
	{
		ArrayList<BatchInfo> batch = daoserv.GetAllBatchesDB();	
		return batch;
		
	}
	
	
	
		
	@RequestMapping(value="/updateAssociates" , method = RequestMethod.POST, consumes ={"application/json"})
	public void updateAssociates(@RequestBody MappedAssociate associates) {
		List<Integer> id = associates.getAssociateId();
		long clientId = associates.getClientId();
		String status = associates.getStatus();
		
		for(int associateId : id)
		{
			daoserv.UpdateStatus(status, associateId, clientId);
		}
		logger.info("Updating!!!!!!!!!!!!!!!!!!!!");
		logger.info(associates);
		logger.info("status " + associates.getStatus());
		
	}
	
	
	

	
	@RequestMapping("/getMonth")
	public @ResponseBody List getMonths(@RequestParam("month") int month)
	{
		ArrayList<String> statuslist = new ArrayList<String>(Arrays.asList("Available", "Mapped", "Confirmed"));
		ArrayList<String> typelist = new ArrayList<String>(Arrays.asList("JAVA", ".NET", "SDET"));
		List returnNumbers = new ArrayList();

		for(String statusparam : statuslist)
		{
			for(String typeparam : typelist)
			{
				int num = daoserv.returnMonthlyResourcesLooping(month, typeparam, statusparam).size();
				returnNumbers.add(num);
			}
		}
		
		
		return returnNumbers;
	}
	
	@RequestMapping("/getAssociates")
	public @ResponseBody List getResources(@RequestParam("month") int month, @RequestParam("type") String type, @RequestParam("status") String status)
	{		
		List<AssociateInfo> associates = daoserv.returnMonthlyResourcesLooping(month, type, status);
		
		return associates;
	}
	
	// --------------------------------------------------------------------------------//
		// ------------------------------- JAVA
		// --------------------------------------------------------------------------------//

		// Mapped Java Number
		@RequestMapping(value = "/displayCurrentJavaMapped", method = RequestMethod.GET)
		public @ResponseBody int displayCurrentJavaMapped() {
			int mappedJava = daoserv.getMappedCurrentJava().size();
			logger.info("Mapped Java Count: "+ mappedJava);
			return mappedJava;

		}

		// Mapped Java List
		@RequestMapping(value = "/displayCurrentJavaMappedList", method = RequestMethod.GET)
		public @ResponseBody List displayCurrentJavaMappedList() {
			List mappedJavaList = daoserv.getMappedCurrentJava();
			logger.info("Mapped Java List: "+ mappedJavaList);
			return mappedJavaList;

		}

		// Confirmed Java Number
		@RequestMapping(value = "/displayCurrentJavaConfirmed", method = RequestMethod.GET)
		public @ResponseBody int displayCurrentJavaConfirmed() {
			int confirmedJava = daoserv.getConfirmedCurrentJava().size();
			logger.info("Confirmed Java Count: "+ confirmedJava);
			return confirmedJava;

		}

		// Confirmed Java List
		@RequestMapping(value = "/displayCurrentJavaConfirmedList", method = RequestMethod.GET)
		public @ResponseBody List displayCurrentJavaConfirmedList() {
			List confirmedJavaList = daoserv.getConfirmedCurrentJava();
			logger.info("Confirmed Java List: "+ confirmedJavaList);
			return confirmedJavaList;

		}

		// Available Java Number
		@RequestMapping(value = "/displayCurrentJavaAvailable", method = RequestMethod.GET)
		public @ResponseBody int displayCurrentJavaAvailable() {
			int availableJava = daoserv.getAvailableCurrentJava().size();
			logger.info("Avaiable Java Count: "+ availableJava);
			return availableJava;

		}

		// Available Java List
		@RequestMapping(value = "/displayCurrentJavaAvailableList", method = RequestMethod.GET)
		public @ResponseBody List displayCurrentJavaAvailableList() {
			List availableJavaList = daoserv.getAvailableCurrentJava();
			logger.info("Available Java List: "+ availableJavaList);
			return availableJavaList;

		}

		// --------------------------------------------------------------------------------//
		// ------------------------------- SDET
		// --------------------------------------------------------------------------------//

		// Mapped SDET Number
		@RequestMapping(value = "/displayCurrentSDETMapped", method = RequestMethod.GET)
		public @ResponseBody int displayCurrentSDETMapped() {
			int mappedSDET = daoserv.getMappedCurrentSDET().size();
			logger.info("Mapped SDET Count: "+ mappedSDET);
			return mappedSDET;
		}

		// Mapped SDET List
		@RequestMapping(value = "/displayCurrentSDETMappedList", method = RequestMethod.GET)
		public @ResponseBody List displayCurrentSDETMappedList() {
			List mappedSDETList = daoserv.getMappedCurrentSDET();
			logger.info("Mapped SDET List: "+ mappedSDETList);
			return mappedSDETList;
		}

		// Confirmed SDET Number
		@RequestMapping(value = "/displayCurrentSDETConfirmed", method = RequestMethod.GET)
		public @ResponseBody int displayCurrentSDETConfirmed() {
			int confirmedSDET = daoserv.getConfirmedCurrentSDET().size();
			logger.info("Confirmed SDET Count: "+ confirmedSDET);
			return confirmedSDET;
		}

		// Confirmed SDET List
		@RequestMapping(value = "/displayCurrentSDETConfirmedList", method = RequestMethod.GET)
		public @ResponseBody List displayCurrentSDETConfirmedList() {
			List confirmedSDETList = daoserv.getConfirmedCurrentSDET();
			logger.info("Confirmed SDET List: "+ confirmedSDETList);
			return confirmedSDETList;
		}

		// Available SDET Number
		@RequestMapping(value = "/displayCurrentSDETAvailable", method = RequestMethod.GET)
		public @ResponseBody int displayCurrentSDETAvailable() {
			int availableSDET = daoserv.getAvailableCurrentSDET().size();
			logger.info("Available SDET Count: "+ availableSDET);
			return availableSDET;
		}

		// Available SDET List
		@RequestMapping(value = "/displayCurrentSDETAvailableList", method = RequestMethod.GET)
		public @ResponseBody List displayCurrentSDETAvailableList() {
			List availableSDETList = daoserv.getAvailableCurrentSDET();
			logger.info("Available SDET Count: "+ availableSDETList);
			return availableSDETList;
		}

		// --------------------------------------------------------------------------------//
		// ------------------------------- .NET
		// --------------------------------------------------------------------------------//

		// Mapped NET Number
		@RequestMapping(value = "/displayCurrentNETMapped", method = RequestMethod.GET)
		public @ResponseBody int displayCurrentNETMapped() {
			int mappedNET = daoserv.getMappedCurrentNET().size();
			logger.info("Mapped .NET Count: "+ mappedNET);
			return mappedNET;
		}

		// Mapped NET List
		@RequestMapping(value = "/displayCurrentNETMappedList", method = RequestMethod.GET)
		public @ResponseBody List displayCurrentNETMappedList() {
			List mappedNETList = daoserv.getMappedCurrentNET();
			logger.info("Mapped .NET List: "+ mappedNETList);
			return mappedNETList;
		}

		// Confirmed NET Number
		@RequestMapping(value = "/displayCurrentNETConfirmed", method = RequestMethod.GET)
		public @ResponseBody int displayCurrentNETConfirmed() {
			int confirmedNET = daoserv.getConfirmedCurrentNET().size();
			logger.info("Confirmed .NET Count: "+ confirmedNET);
			return confirmedNET;
		}

		// Confirmed NET Number
		@RequestMapping(value = "/displayCurrentNETConfirmedList", method = RequestMethod.GET)
		public @ResponseBody List displayCurrentNETConfirmedList() {
			List confirmedNETList = daoserv.getConfirmedCurrentNET();
			logger.info("Confirmed .NET List: "+ confirmedNETList);
			return confirmedNETList;
		}

		// Available NET Number
		@RequestMapping(value = "/displayCurrentNETAvailable", method = RequestMethod.GET)
		public @ResponseBody int displayCurrentNETAvailable() {
			int availableNET = daoserv.getAvailableCurrentNET().size();
			logger.info("Available .NET Count: "+ availableNET);
			return availableNET;
		}

		// Available NET Number
		@RequestMapping(value = "/displayCurrentNETAvailableList", method = RequestMethod.GET)
		public @ResponseBody List displayCurrentNETAvailableList() {
			List availableNETList = daoserv.getAvailableCurrentNET();
			logger.info("Available .NET List: "+ availableNETList);
			return availableNETList;
		}

		@RequestMapping(value="/updateAvailableAssociates" , method = RequestMethod.POST, consumes ={"application/json"})
		public void updateAvailableAssociates(@RequestBody AvailableAssociate associates) {
			
			logger.info("Updating!!!!!!!!!!!!!!!!!!!!!");
			logger.info(associates);
			long client = associates.getClient();
			List associateList = associates.getAssociateId();
			daoserv.updateAvailableAssociates(associateList, client);
		}

		@RequestMapping(value="/updateMappedAssociates" , method = RequestMethod.POST, consumes ={"application/json"})
		public void updateMappedAssociates(@RequestBody MappedAssociate associates) {
			
			logger.info("Updating!!!!!!!!!!!!!!!!!!!!");
			logger.info(associates);
			String status = associates.getStatus();
			List associateList = associates.getAssociateId();
			daoserv.updateMappedAssociates(associateList, status);
		}
		
		@RequestMapping(value="/updateConfirmedAssociates" , method = RequestMethod.POST, consumes ={"application/json"})
		public void updateConfirmedAssociates(@RequestBody ConfirmedAssociate associates) {
			//daoserv.UpdateStatus(status, id, client);
			logger.info("Updating!!!!!!!!!!!!!!!!!!!!");
			logger.info(associates);
			List associateList = associates.getAssociateId();
			daoserv.updateConfirmedAssociates(associateList);
		}

}