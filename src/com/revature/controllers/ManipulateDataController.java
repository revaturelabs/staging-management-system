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
import com.revature.classes.MappedAssociate;
import com.revature.classes.Week;

@RestController
public class ManipulateDataController {

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
	
	
	@RequestMapping(value="/displayCurrent", method = RequestMethod.GET)
	public @ResponseBody List[] displayCurrent()
	{
		// create a list for the confirmed associates
		List confirmed = new ArrayList();
		confirmed.add("confirmed");
		String[] conId = {"confirmed-java", "confirmed-net", "confirmed-sdet"};
		confirmed.add(conId);

		// get the associates from the database
		List conJava = daoserv.getConfirmedCurrentJava();
		List conNet = daoserv.getConfirmedCurrentNET();
		List conSdet = daoserv.getConfirmedCurrentSDET();
				
		// add the associates to the list
		confirmed.add(conJava);
		confirmed.add(conNet);
		confirmed.add(conSdet);
		
		// add the number of associates
		confirmed.add(conJava.size());
		confirmed.add(conNet.size());
		confirmed.add(conSdet.size());
		
		// create a list for the mapped associates
		List mapped = new ArrayList();
		mapped.add("mapped");
		String[] mapId = {"mapped-java", "mapped-net", "mapped-sdet"};
		mapped.add(mapId);
		
		// get the associates from the database
		List mapJava = daoserv.getMappedCurrentJava();
		List mapNet = daoserv.getMappedCurrentNET();
		List mapSdet = daoserv.getMappedCurrentSDET();
		
		// add the associates to the list
		mapped.add(mapJava);
		mapped.add(mapNet);
		mapped.add(mapSdet);
		
		// add the number of associates
		mapped.add(mapJava.size());
		mapped.add(mapNet.size());
		mapped.add(mapSdet.size());
		
		// create a list for the available associates
		List available = new ArrayList();
		available.add("available");
		String[] availId = {"available-java", "available-net", "available-sdet"};
		available.add(availId);
		
		// get the associates from the database
		List availJava = daoserv.getAvailableCurrentJava();
		List availNet = daoserv.getAvailableCurrentNET();
		List availSdet = daoserv.getAvailableCurrentSDET();
		
		// add the associates to the list
		available.add(availJava);
		available.add(availNet);
		available.add(availSdet);
		
		// add the number of associates
		available.add(availJava.size());
		available.add(availNet.size());
		available.add(availSdet.size());
		
		// add all the lists to a list
		List[] allData = {
				available, mapped, confirmed
		};
		
		// return the list containing all of the lists
		return allData; 
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
		System.out.println("Updating!!!!!!!!!!!!!!!!!!!!");
		System.out.println(associates);
		System.out.println("status " + associates.getStatus());
		
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
}