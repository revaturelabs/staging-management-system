package com.revature.classes;

import java.util.ArrayList;
import java.util.List;

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


	public List getCurrentJava();
	
	public List getCurrentSDET();
	
	public List getCurrentNET();
}
