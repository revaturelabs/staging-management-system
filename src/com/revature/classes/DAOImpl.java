package com.revature.classes;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Configuration
@EnableCaching
public class DAOImpl implements DAOService {

	SessionFactory sf;

	public void setSf(SessionFactory sf) {
		this.sf = sf;
	}
	
	
	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
	public void AddClient(ClientInfo ci) {
		
		// create session object
		Session session = sf.getCurrentSession();
		//add client to db
		session.saveOrUpdate(ci);
		
		//System.out.println("done inserting client");
		
	}

	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
	public void AddAssociate(AssociateInfo ai) {
		
		// create session object
		Session session = sf.getCurrentSession();
		//add client to db
		session.saveOrUpdate(ai);
				
		//System.out.println("done inserting asso");
				
	}

	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
	public void AddBatch(BatchInfo bi) {
		// create session object
		Session session = sf.getCurrentSession();
		//add client to db
		session.saveOrUpdate(bi);
				
		//System.out.println("done inserting batch");
	}

	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
	public ClientInfo GetClientFromDB(long id) {
		ClientInfo client = null;
		
		Session session = sf.getCurrentSession();

		Criteria criteria = session.createCriteria(ClientInfo.class);
		criteria.add((Restrictions.like("ClientID", id)));
		client = (ClientInfo) criteria.uniqueResult();
		return client;
	}

	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
	public AssociateInfo GetAssociateFromDB(long id) {
		
		AssociateInfo associate = null;
		
		Session session = sf.getCurrentSession();

		Criteria criteria = session.createCriteria(AssociateInfo.class);
		criteria.add((Restrictions.like("AssociateID", id)));
		associate = (AssociateInfo) criteria.uniqueResult();
		return associate;
	}

	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
	public BatchInfo GetBatchFromDB(String id) {
		BatchInfo batch = null;
		
		Session session = sf.getCurrentSession();

		Criteria criteria = session.createCriteria(BatchInfo.class);
		criteria.add((Restrictions.like("TrainingName", id)));
		batch = (BatchInfo) criteria.uniqueResult();
		return batch;
	}


	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
	public ArrayList<AssociateInfo> GetAllAssociatesDB() {
		
		ArrayList<AssociateInfo> assoList = new ArrayList<AssociateInfo>();
		
		Session session = sf.getCurrentSession();
		
		Criteria cr = session.createCriteria(AssociateInfo.class);
		List<AssociateInfo> list = cr.list();
		
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			AssociateInfo asso = (AssociateInfo) iterator.next();
			// System.out.println(asso);
			assoList.add(asso);
		}
		return assoList;
		
	}


	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
	public ArrayList<BatchInfo> GetAllBatchesDB() {
		
		ArrayList<BatchInfo> batchList = new ArrayList<BatchInfo>();
		
		Session session = sf.getCurrentSession();
		
		Criteria cr = session.createCriteria(BatchInfo.class);
		List<BatchInfo> list = cr.list();
		
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			BatchInfo batch = (BatchInfo) iterator.next();
			// System.out.println(asso);
			batchList.add(batch);
		}
		return batchList;
		
	}


	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
	public ArrayList<ClientInfo> GetAllClientsDB() {
		
		ArrayList<ClientInfo> clientList = new ArrayList<ClientInfo>();
		
		Session session = sf.getCurrentSession();
		
		Criteria cr = session.createCriteria(ClientInfo.class);
		List<ClientInfo> list = cr.list();
		
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			ClientInfo client = (ClientInfo) iterator.next();
			// System.out.println(asso);
			clientList.add(client);
		}
		return clientList;
		
	}

	//LETS MAKE SOME WEEEEEEEKS
		@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
		public ArrayList<Week> createWeeks()
		{
			ArrayList<Week> WeekList = new ArrayList<Week>();
			
			int dateIncrement = 1;
			for(int weekcounter = 0; weekcounter < 52; weekcounter++) //currently we always want a 52 week overalll outlook
			{
				//populate the week object with the proper datestring
				Week currentweek = new Week();
				
				Date date = new Date();
				Calendar c = Calendar.getInstance();
				c.setTime(date);
				int i = c.get(Calendar.DAY_OF_WEEK) - c.getFirstDayOfWeek();//find the monday and set it for Date start
				
				c.add(Calendar.DATE, -i + dateIncrement);
				Date start = c.getTime();
				c.add(Calendar.DATE, 4);
				Date end = c.getTime();
				
				//while were here, make start and end the appropriate stand and end of week parameters to pass to the named query
				java.sql.Date weekstartparam = new java.sql.Date(start.getTime());
				java.sql.Date weekendparam = new java.sql.Date(end.getTime());
				
			    SimpleDateFormat startformat = new SimpleDateFormat("MMM dd");
			    String range1 = startformat.format(start);
			    
			    SimpleDateFormat endformat = new SimpleDateFormat("MMM dd");
			    String range2 = endformat.format(end);
			    
			    String daterange = (range1 + " - " + range2);
			    //Now get the total number of java batch resources for this week
			    java.util.Date utildate = new java.util.Date();
			    java.sql.Date sqldate = new java.sql.Date(utildate.getTime()); //today
			    int weeklyJavaResources = getNumJava(sqldate, weekstartparam, weekendparam);
			    int weeklyNETResources = getNumNET(sqldate, weekstartparam, weekendparam);
			    int weeklySDETResources = getNumSDET(sqldate, weekstartparam, weekendparam);
			    
			    currentweek.setDate(date);
			    currentweek.setStartdate(start);
			    currentweek.setEnddate(end);
			    currentweek.setJavacount(weeklyJavaResources);
			    currentweek.setDotNetCount(weeklyNETResources);
			    currentweek.setSdetcount(weeklySDETResources);
			    currentweek.setDaterange(daterange);
			    
			    
			    //add this week to the overall list 
			    WeekList.add(currentweek);
			    //increment relevant external variables
			    dateIncrement +=7;
			}
			
			return WeekList;
		}
		
		@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
	    public int getNumJava(java.sql.Date date, java.sql.Date startdate, java.sql.Date enddate)
	    {
	        Session session = sf.getCurrentSession();
	        //initializes cirteria using the associateInfo pojo
	        Criteria crit = session.createCriteria(AssociateInfo.class,"ai");
	        //creates an alias for the join between associateInfo and batch that is in the associateInfo pojo
	        crit.createAlias("ai.batch", "batch");
	        //search for batch type = java
	        crit.add(Restrictions.eq("batch.Type", "JAVA"));
	        List rowBatch = crit.list();
	        int count = rowBatch.size();
	        System.out.println(count);
	        return count;
	    }
		
		@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
	    public int getNumSDET(java.sql.Date date, java.sql.Date startdate, java.sql.Date enddate)
	    {
	        Session session = sf.getCurrentSession();
	        //initializes cirteria using the associateInfo pojo
	        Criteria crit = session.createCriteria(AssociateInfo.class,"ai");
	        //creates an alias for the join between associateInfo and batch that is in the associateInfo pojo
	        crit.createAlias("ai.batch", "batch");
	        //search for batch type = java
	        crit.add(Restrictions.eq("batch.Type", "SDET"));
	        List rowBatch = crit.list();
	        int count = rowBatch.size();
	        System.out.println(count);
	        return count;
	    }
		
		
		@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
	    public int getNumNET(java.sql.Date date, java.sql.Date startdate, java.sql.Date enddate)
	    {
	        Session session = sf.getCurrentSession();
	        //initializes cirteria using the associateInfo pojo
	        Criteria crit = session.createCriteria(AssociateInfo.class,"ai");
	        //creates an alias for the join between associateInfo and batch that is in the associateInfo pojo
	        crit.createAlias("ai.batch", "batch");
	        //search for batch type = java
	        crit.add(Restrictions.eq("batch.Type", "NET"));
	        List rowBatch = crit.list();
	        int count = rowBatch.size();
	        System.out.println(count);
	        return count;
	    }
		
		
	}
