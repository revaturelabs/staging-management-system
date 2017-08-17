package com.revature.sms.services;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.sms.entities.Associate;
import com.revature.sms.entities.Panel;
import com.revature.sms.repositories.PanelRepo;
@Service
public class PanelServiceImpl implements PanelService
{
	@Autowired
	PanelRepo pr;
	
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
	
	public Set<Panel> getAllPanel()
	{
		
		return new HashSet<>(pr.findAll());
		
	}

	@Override
	public void addPanel(Panel panel) 
	{
		pr.saveAndFlush(panel);
	}
	
	
}
