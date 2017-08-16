package com.revature.sms.services;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import com.revature.sms.entities.Associate;
import com.revature.sms.entities.Panel;
import com.revature.sms.repositories.PanelRepo;

public class PanelServiceImpl implements PanelService
{
	@Autowired
	PanelRepo pr;

	@Override
	public void add(Panel panel) 
	{
		pr.saveAndFlush(panel);
		
	}

	@Override
	public Set<Panel> findByAssociate(Associate associate) 
	{
		 
		return  pr.getByAssociate(associate);
	}

	@Override
	public void update(Panel panel) 
	{
		pr.saveAndFlush(panel);
		
	}
	
	public Panel getById(long id)
	{
		
		return pr.findOne(id);
		
	}
	
	
	
}
