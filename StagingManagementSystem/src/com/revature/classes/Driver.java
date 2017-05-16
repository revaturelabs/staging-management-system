package com.revature.classes;

import java.sql.Date;
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
	


	public static void main(String[] args) {
				
		SessionFactory sf = new Configuration().configure().buildSessionFactory();
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();

		Calendar currenttime = Calendar.getInstance();
		Date sqldate = new Date((currenttime.getTime()).getTime());
		
		
		// create a batch
		BatchInfo batch = new BatchInfo("Java 101", "Reston, VA", "Ankit", sqldate, sqldate, "Java");
		session.save(batch);

		
		// create a client
		ClientInfo client = new ClientInfo("Accenture", "Austin, Texas");
		//session.save(client);
		session.save(client);
		
		// add client to a list of clients
	    Set<ClientInfo> clients = new HashSet<ClientInfo>();
	    clients.add(client);

	    // create an associate
		AssociateInfo associate = new AssociateInfo("Bily Bob", "available", clients);
		session.save(associate);
		
		/*session.save(associate);
		
		tx.commit();
		session.close();
		sf.close(); */
				
		System.out.println(batch.toString());
		System.out.println(associate.toString());
		System.out.println(client.toString());
		
		tx.commit();
		session.close();
		sf.close();

	}
	
}
