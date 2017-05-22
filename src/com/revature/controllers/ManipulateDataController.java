package com.revature.controllers;

import javax.ws.rs.FormParam;

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
import com.revature.classes.DAOService;
import com.revature.classes.Week;

@RestController
public class ManipulateDataController {

	ApplicationContext ctx = new ClassPathXmlApplicationContext("appContext.xml");
	DAOService daoserv = (DAOService) ctx.getBean("DAOImpl");
	
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
			// daoserv.login(username, password);
			return "Welcome, " + username;
		} catch (Exception e) {
			System.out.println("Error: username or password not found.");
			String msg = "Username or Password not found";
			return msg;
		}
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
	public @ResponseBody ArrayList<AssociateInfo> ViewAssociateStats(HttpServletRequest req) throws JsonGenerationException, JsonMappingException, IOException
	{
		ArrayList<AssociateInfo> associates = daoserv.GetAllAssociatesDB();
    	return associates;
	}
	
	@RequestMapping(value = "/displayClients", method = RequestMethod.POST)
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
	
	@RequestMapping(value="/displayWeeks", method = RequestMethod.GET)
	public @ResponseBody List displayWeeks()
	{
		// testing data - remove once actual data is acquired
		Week week = new Week();
		week.setDaterange("May 5 - May 13");
		week.setDotNetCount(25);
		week.setJavacount(49);
		week.setSdetcount(12);
		
		Week week2 = new Week();
		week2.setDaterange("May 14 - May 20");
		week2.setDotNetCount(15);
		week2.setJavacount(12);
		week2.setSdetcount(19);
		
		Week week3 = new Week();
		week3.setDaterange("May 21 - May 28");
		week3.setDotNetCount(22);
		week3.setJavacount(19);
		week3.setSdetcount(10);
		
		List<Week> weeks = new ArrayList<Week>();
		weeks.add(week);
		weeks.add(week2);
		weeks.add(week3);
		
		// List<Week> weeks = daoserv.createWeeks();
		System.out.println(weeks);
		return weeks;
	}
	
	@RequestMapping(value="/displayCurrent", method = RequestMethod.GET)
	public @ResponseBody List[] displayCurrent()
	{
		
		// get all of the current confirmed associates
		List confirmed = new ArrayList();
		confirmed.add("confirmed");
		
		List conJava = daoserv.getConfirmedCurrentJava();
		List conNet = daoserv.getConfirmedCurrentNET();
		List conSdet = daoserv.getConfirmedCurrentSDET();
		
		confirmed.add(conJava);
		confirmed.add(conNet);
		confirmed.add(conSdet);
		
		confirmed.add(conJava.size());
		confirmed.add(conNet.size());
		confirmed.add(conSdet.size());
		
		// get all of the current mapped associates
		List mapped = new ArrayList();
		mapped.add("mapped");
		
		List mapJava = daoserv.getMappedCurrentJava();
		List mapNet = daoserv.getMappedCurrentNET();
		List mapSdet = daoserv.getMappedCurrentSDET();
		
		mapped.add(mapJava);
		mapped.add(mapNet);
		mapped.add(mapSdet);
		
		mapped.add(mapJava.size());
		mapped.add(mapNet.size());
		mapped.add(mapSdet.size());
		
		// get all of the current confirmed associates
		List available = new ArrayList();
		available.add("available");
		
		List availJava = daoserv.getAvailableCurrentJava();
		List availNet = daoserv.getAvailableCurrentNET();
		List availSdet = daoserv.getAvailableCurrentSDET();
		
		available.add(availJava);
		available.add(availNet);
		available.add(availSdet);
		
		available.add(availJava.size());
		available.add(availNet.size());
		available.add(availSdet.size());
		
		// add all the lists to a list
		List[] allData = {
				available, mapped, confirmed
		};
		
		
		// return the list containing all of the lists
		return allData;
		
		
		/*
		// testing data - remove once actual data is acquired
				Week week = new Week();
				week.setDaterange("Available");
				week.setDotNetCount(25);
				week.setJavacount(49);
				week.setSdetcount(12);
				
				Week week2 = new Week();
				week2.setDaterange("Mapped");
				week2.setDotNetCount(15);
				week2.setJavacount(12);
				week2.setSdetcount(19);
				
				Week week3 = new Week();
				week3.setDaterange("Confirmed");
				week3.setDotNetCount(22);
				week3.setJavacount(19);
				week3.setSdetcount(10);
				
				List<Week> weeks = new ArrayList<Week>();
				weeks.add(week);
				weeks.add(week2);
				weeks.add(week3);
		
		return weeks;
		*/
	}
	
	@RequestMapping("/updateAssociates")
	public void updateAssociates(@RequestBody long[] id, @RequestBody String status, @RequestBody int client)
	{
		daoserv.UpdateStatus(status, id, client);
	}
	

}