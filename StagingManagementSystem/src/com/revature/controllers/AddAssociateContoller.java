package com.revature.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AddAssociateContoller {
	
	@RequestMapping(value = "/AddAssociateForm", method = RequestMethod.POST)
	public String PostService(HttpServletRequest request) {
		String param1 = request.getParameter("name");
		System.out.println(param1);
		String param2 = request.getParameter("location");
		System.out.println(param2);
		String param3 = request.getParameter("phone");
		System.out.println(param3);
		
		return "This works";
	}
}
