package com.revature.classes;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public interface DAOService {
	
	public void AddClient(ClientInfo ci);
	
	public void AddAssociate(AssociateInfo ai);
	
	public void AddBatch(BatchInfo bi);
	
	public ClientInfo GetClientFromDB(long id);
	
	public AssociateInfo GetAssociateFromDB(long id);
	
	public BatchInfo GetBatchFromDB(String id);
	
	public ArrayList<AssociateInfo> GetAllAssociatesDB();
	
	public ArrayList<BatchInfo> GetAllBatchesDB();

	public ArrayList<ClientInfo> GetAllClientsDB();
	
	public ArrayList<Week> createWeeks();
		
	public ArrayList returnResources(java.sql.Date date, java.sql.Date startdate, java.sql.Date enddate);

	public List getAllCurrentJava();
	
	public List getAllCurrentSDET();
	
	public List getAllCurrentNET();
	
	public List getAvailableCurrentJava();

	public List getAvailableCurrentSDET();

	public List getAvailableCurrentNET();

	public List getMappedCurrentJava();

	public List getMappedCurrentSDET();
	
	public List getMappedCurrentNET();
	
	public List getConfirmedCurrentJava();
	
	public List getConfirmedCurrentSDET();
	
	public List getConfirmedCurrentNET();

	public void UpdateStatus(String status, long[] id, int client);

	public ArrayList<Week> returnWeeksForGivenMonth(int month) throws ParseException;

	public ArrayList<Set> returnMonthlyResources(int month);
}