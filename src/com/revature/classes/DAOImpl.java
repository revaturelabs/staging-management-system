package com.revature.classes;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
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

	public void setSf(SessionFactory sf) 
	{
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
			   
			    /*
			    //THE OLD WAY
			    //Now get the total number of java batch resources for this week
			    java.util.Date utildate = new java.util.Date();
			    java.sql.Date sqldate = new java.sql.Date(utildate.getTime()); //today
			    
			    
			    int weeklyJavaResources = getNumJava(sqldate, weekstartparam, weekendparam);
			    int weeklyNETResources = getNumNET(sqldate, weekstartparam, weekendparam);
			    int weeklySDETResources = getNumSDET(sqldate, weekstartparam, weekendparam);
			    
			    
			    ArrayList<Integer> resources = returnResources(sqldate, weekstartparam, weekendparam);
			    
			    currentweek.setDate(date);
			    currentweek.setStartdate(start);
			    currentweek.setEnddate(end);
			    currentweek.setJavacount(resources.get(0));
			    currentweek.setDotNetCount(resources.get(1));
			    currentweek.setSdetcount(resources.get(2));
			    currentweek.setDaterange(daterange);
			    
			    
			    //add this week to the overall list 
			    WeekList.add(currentweek);
			    */
			    Session session = sf.getCurrentSession();
			    java.util.Date utildate = new java.util.Date();
			    java.sql.Date sqldate = new java.sql.Date(utildate.getTime());
			    
			    
			    Query query = session.createSQLQuery(
		                "CALL GETWEEKLYJAVARESOURCES(:STARTOFWEEK, :ENDOFWEEK, :CURRENTDATE)")
		                .addEntity(Week.class)
		                .setParameter("STARTOFWEEK", weekstartparam)
		                .setParameter("ENDOFWEEK", weekendparam)
		                .setParameter("CURRENTDATE", sqldate);

		            List result = query.list();
		            for(int j=0; j<result.size(); j++){
		                Week week = (Week)result.get(j);
		                WeekList.add(week);
		            }
			    
			    //increment relevant external variables
			    dateIncrement +=7;
			}
			
			return WeekList;
		}
		
	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
	public ArrayList returnResources(java.sql.Date date, java.sql.Date startdate, java.sql.Date enddate)
	{
		/*
		int [] resources = new int[3];
		Session session = sf.getCurrentSession();
		
		//JAVA
		
		//initializes cirteria using the associateInfo pojo
        Criteria critjava = session.createCriteria(AssociateInfo.class,"ai");
        //creates an alias for the join between associateInfo and batch that is in the associateInfo pojo
        critjava.createAlias("ai.batch", "batch");
        //search for batch type = java
        critjava.add(Restrictions.eq("batch.Type", "JAVA"));
        List rowBatch = critjava.list();
        resources[0] = rowBatch.size();
        
        //.NET
        
        Criteria critnet = session.createCriteria(AssociateInfo.class, "ai");
        critnet.createAlias("ai.batch", "batch");
        */
		
		ArrayList<Integer> resources = new ArrayList<Integer>();
		return resources;
		
	}
		
	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    public List getCurrentJava()
    {
        Session session = sf.getCurrentSession();
        
        //initializes criteria using the associateInfo pojo
        Criteria crit = session.createCriteria(AssociateInfo.class,"ai");
        
        //creates an alias for the join between associateInfo and batch that is in the associateInfo pojo
        crit.createAlias("ai.batch", "batch");
        
        //create a current date for today
        java.util.Date utildate = new java.util.Date();
	    java.sql.Date sqldate = new java.sql.Date(utildate.getTime()); //today
        
	    //search for batch type = java
        crit.add(Restrictions.eq("batch.Type", "JAVA"));
        
        //search for those before the current date
        crit.add(Restrictions.le("batch.EndDate", sqldate));
        
        //put the result into a list 
        List rowBatch = crit.list();
        
        System.out.println("all people who did java" + rowBatch);
        int count = rowBatch.size();
        System.out.println(count);
        
        return rowBatch;
    }
	
	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    public List getCurrentSDET()
    {
//        Session session = sf.getCurrentSession();
//        //initializes cirteria using the associateInfo pojo
//        Criteria crit = session.createCriteria(AssociateInfo.class,"ai");
//        //creates an alias for the join between associateInfo and batch that is in the associateInfo pojo
//        crit.createAlias("ai.batch", "batch");
//        //search for batch type = java
//        crit.add(Restrictions.eq("batch.Type", "SDET"));
//        List rowBatch = crit.list();
//        int count = rowBatch.size();
//        System.out.println(count);
//        return count;
		 Session session = sf.getCurrentSession();
	        
	     //initializes criteria using the associateInfo pojo
	     Criteria crit = session.createCriteria(AssociateInfo.class,"ai");
	        
	     //creates an alias for the join between associateInfo and batch that is in the associateInfo pojo
	     crit.createAlias("ai.batch", "batch");
	        
	     //create a current date for today
	     java.util.Date utildate = new java.util.Date();
		 java.sql.Date sqldate = new java.sql.Date(utildate.getTime()); //today
	        
		 //search for batch type = java
	     crit.add(Restrictions.eq("batch.Type", "SDET"));
	       
	     //search for those before the current date
	     crit.add(Restrictions.le("batch.EndDate", sqldate));
	        
	     //put the result into a list 
	     List rowBatch = crit.list();
	       
	     System.out.println("all people who are did SDET ..." + rowBatch);
	     int count = rowBatch.size();
	     System.out.println(count);
	        
	     return rowBatch;
    }
	
	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    public List getCurrentNET()
    {
//        Session session = sf.getCurrentSession();
//        //initializes cirteria using the associateInfo pojo
//        Criteria crit = session.createCriteria(AssociateInfo.class,"ai");
//        //creates an alias for the join between associateInfo and batch that is in the associateInfo pojo
//        crit.createAlias("ai.batch", "batch");
//        //search for batch type = java
//        crit.add(Restrictions.eq("batch.Type", "NET"));
//        List rowBatch = crit.list();
//        int count = rowBatch.size();
//        System.out.println(count);
//        return count;
		Session session = sf.getCurrentSession();
        
	    //initializes criteria using the associateInfo pojo
	    Criteria crit = session.createCriteria(AssociateInfo.class,"ai");
	        
	    //creates an alias for the join between associateInfo and batch that is in the associateInfo pojo
	    crit.createAlias("ai.batch", "batch");
	        
	    //create a current date for today
	    java.util.Date utildate = new java.util.Date();
		java.sql.Date sqldate = new java.sql.Date(utildate.getTime()); //today
	        
		//search for batch type = java
	    crit.add(Restrictions.eq("batch.Type", ".NET"));
	       
	    //search for those before the current date
	    crit.add(Restrictions.le("batch.EndDate", sqldate));
	        
	    //put the result into a list 
	    List rowBatch = crit.list();
	       
	    System.out.println("all people who are did NET ..." + rowBatch);
	    int count = rowBatch.size();
	    System.out.println(count);
	        
	    return rowBatch;
    }
	
	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
	public void UpdateStatus(String status, long[] aID, int clientId) {
		
		Session session = sf.openSession();

		String hqlUpdate = "update AssociateInfo a set a.Status = :newStatus where a.AssociateID = :ID";
		// or String hqlUpdate = "update Customer set name = :newName where name = :oldName";
		for(int i = 0; i < aID.length; i++) 
		{
			int updatedEntities = session.createQuery( hqlUpdate )
		        .setString( "newStatus", status )
		        .setLong( "ID", aID[i] )
		        .executeUpdate();
		}
		
		session.close();
		
	}

	
		
}