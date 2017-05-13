package com.revature.controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.FormParam;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.revature.classes.DAOService;
import com.revature.classes.Test;

@RestController
public class ManipulateDataController {
	
	ApplicationContext ctx = new ClassPathXmlApplicationContext("springmvc-servlet.xml");
	DAOService daoserv = (DAOService) ctx.getBean("DAOImpl");

	@RequestMapping(value="/login", method=RequestMethod.POST)	
	public String login(@FormParam("username") String username, @FormParam("password") String password)
	{
		// try to get a single result from the database
		// if a single result cannot be returned from the database
		// return the user to the login page and display a notification alert
		// else, send the user to the main page
		try 
		{
			// daoserv.login(username, password);
			return "Welcome, " + username;
		} catch(Exception e)
		{
			System.out.println("Error: username or password not found.");
			String msg = "Username or Password not found";
			return msg;
		}	
	}
	
	@RequestMapping(value = "/AddAssociateForm", method = RequestMethod.POST)
	public @ResponseBody String PostService(@RequestBody Test mytest, HttpServletRequest request) throws IOException 
	{
		System.out.println(request.getParameter("name"));
		System.out.println(request.getParameter("location"));
		System.out.println(request.getParameter("phone"));
		System.out.println(mytest.toString());
		
		return "bless up";
	}
	/*
	@RequestMapping(value = "/AddBatch", method = RequestMethod.POST)
	public @ResponseBody String addBatch(@RequestBody Batch mybatch, HttpServletRequest request) throws IOException
	{
		System.out.println(request.getParameter("batchsize"));
		System.out.println(request.getParameter("batchtype"));
		System.out.println(request.getParameter("batchdate"));
		
	}
	*/
	/*
	@RequestMapping(value = "/AddClient", method = RequestMethod.POST)
	public @ResponseBody String addClient(@RequestBody Client myclient, HttpServletRequest request) throws IOException
	{
		System.out.println(request.getParameter("clientname"));
		System.out.println(request.getParameter("clienttype"));
		System.out.println(request.getParameter("clientnumrequested"));
	}
	*/
}
