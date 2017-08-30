package com.revature.sms.services;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.sms.entities.Associate;
import com.revature.sms.entities.Panel;
import com.revature.sms.repositories.AssociateRepo;
import com.revature.sms.repositories.PanelRepo;
@Service
public class PanelServiceImpl implements PanelService
{
	@Autowired
	PanelRepo pr;
	
	@Autowired
	AssociateRepo associateRepo;
	
	@Override
	public Set<Panel> findByAssociate(Associate associate) 
	{
		 
		return  pr.getByAssociate(associate);
	}

	@Override
	public Set<Panel> findByAssociateId(long id) {
		return pr.findByAssociateId(id);
	}
	
	
	
	@Override
	public void update(Panel panel) 
	{
		LocalDateTime currentDate = LocalDateTime.now();
		panel.setStatusDate(currentDate);
		Associate associate = panel.getAssociate();
		associate.setLastestPanelStatus(panel.getStatus());
		pr.saveAndFlush(panel);
		associateRepo.saveAndFlush(associate);
	
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
	@Transactional
	public void addPanel(Panel panel) 
	{
		LocalDateTime currentDate = LocalDateTime.now();
		panel.setStatusDate(currentDate);
		Associate associate = panel.getAssociate();
		associate.setLastestPanelStatus(panel.getStatus());
		pr.saveAndFlush(panel);
		associateRepo.saveAndFlush(associate);
	}


	
}
