package com.revature.classes;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Driver
{
	static SessionFactory sf;

	public void setSf(SessionFactory sf) 
	{
		this.sf = sf;
	}

	public static void main(String[] args)
	{

		
		
		
		
		// THIS IS WHERE WE DO THE SEEQUL

		// HELP ME ITERATION 2 YOURE MY ONLY HOPE! THEYVE KIDNAPPED ME AND MADE
		// ME NOT NOT HARDCODE THE YEAR
		LocalDate date = LocalDate.now();
		// int rightMonth = date.getMonthValue();
		int rightYear = date.getYear();

		// use these to create a sqldate with the proper parameters
		LocalDate rightdate = LocalDate.of(rightYear, 5, 15);
		LocalDate leftdate = rightdate.minusMonths(1);

		System.out.println(rightdate);
		System.out.println(leftdate);

		// use criteria to list the results
		Session session = sf.getCurrentSession();
		Criteria critt = session.createCriteria(AssociateInfo.class, "ai");

		critt.createAlias("ai.batch", "batch");

		critt.add(Restrictions.eq("batch.Type", "JAVA"));
		critt.add(Restrictions.eq("Status", "Available"));
		critt.add(Restrictions.between("batch.EndDate", leftdate, rightdate));
		
		List resultList = critt.list();
		
		System.out.println(resultList);

	}

}
