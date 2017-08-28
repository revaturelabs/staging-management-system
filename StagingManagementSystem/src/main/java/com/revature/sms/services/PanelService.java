package com.revature.sms.services;

import java.util.Set;

import com.revature.sms.entities.Associate;
import com.revature.sms.entities.Panel;

public interface PanelService {

	// Create
	public void addPanel(Panel panel);

	// Read
	public Set<Panel> findByAssociate(Associate associate);
	public Set<Panel> findByAssociateId(long associateId);
	public Panel getById(long id);
	public Set<Panel> getAllPanel();
	
	// Update
	public void update(Panel panel);
	
	
	
	
}
