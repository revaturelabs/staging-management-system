package com.revature.sms.services;

import java.util.Set;

import com.revature.sms.entities.Associate;
import com.revature.sms.entities.Panel;

public interface PanelService {

	// Create
	public void add(Panel panel);

	// Read
	Set<Panel> findByAssociate(Associate associate);
	
	public void update(Panel panel);
	
	public Panel getById(long id);
	
	
}
