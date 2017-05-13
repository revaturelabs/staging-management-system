package com.revature.controllers;

import com.revature.classes.*;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;



@Controller
public class AddAssociateContoller {
	
	//ApplicationContext apc = new FileSystemXmlApplicationContext("appContext.xml");
	//DAOImpl dao = (DAOImpl) apc.getBean("DAOImpl");
	
	@RequestMapping(value = "/AddAssociateForm", method = RequestMethod.POST)
	public String PostService(HttpServletRequest request) {
		String param1 = request.getParameter("name");
		System.out.println(param1);
		String param2 = request.getParameter("location");
		System.out.println(param2);
		String param3 = request.getParameter("phone");
		System.out.println(param3);
		
		

		//dao.methodname(paramname, paramname2);
		
		return "This works";
	}
}
