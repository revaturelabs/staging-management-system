package com.revature.controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.revature.classes.Test;

@Controller
public class AddAssociateContoller {
	
	@RequestMapping(value = "/AddAssociateForm", method = RequestMethod.POST)
	public @ResponseBody String PostService(@RequestBody Test myfuckingtest, HttpServletRequest request) throws IOException 
	{
		System.out.println(request.getParameter("name"));
		System.out.println(request.getParameter("location"));
		System.out.println(request.getParameter("phone"));
		System.out.println(myfuckingtest.toString());
		
		return "bless up";
	}
}
