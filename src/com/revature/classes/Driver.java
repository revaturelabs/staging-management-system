package com.revature.classes;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Driver {
	


	public static void main(String[] args) 
	{
				
		SessionFactory sf = new Configuration().configure().buildSessionFactory();
		
		ApplicationContext ctx = new ClassPathXmlApplicationContext("appContext.xml");
		DAOService daoserv = (DAOService) ctx.getBean("DAOImpl");
		

		sf.close();

	}
	
}
