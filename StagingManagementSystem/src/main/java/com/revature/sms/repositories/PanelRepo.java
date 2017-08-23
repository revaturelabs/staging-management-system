package com.revature.sms.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.sms.entities.Associate;
import com.revature.sms.entities.Panel;

import java.util.Set;

public interface PanelRepo extends JpaRepository<Panel, Long> {
	
	//Read
	 Set<Panel>  getByAssociate(Associate associate);
	 Set<Panel> findByAssociateId(long id);


	 
	 
}
