package com.revature.sms.controllers.rest;

import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.sms.entities.Associate;
import com.revature.sms.entities.Panel;
import com.revature.sms.services.PanelService;

@RestController
@RequestMapping("panel")
public class PanelControllerImp {

	@Autowired
	PanelService pr;

	@GetMapping("associatetepanel")
	public Set<Panel> getAssociatePanel(HttpSession session) {
		return pr.findByAssociate((Associate) session.getAttribute("LA"));

	}
	
	@GetMapping("/associate/{associateId}")
	public Set<Panel> findByAssociate(@PathVariable String associateId) {
		//long associate_Id = Long.parseLong(associateId);
		Integer associate_Id = Integer.parseInt(associateId);
		return pr.findByAssociateId(associate_Id);
	}

	@GetMapping("allpanel")
	public Set<Panel> SetgetAllPanel() {
		return pr.getAllPanel();

	}

	@PostMapping
	public void addPanel(@RequestBody Panel panel) {
		pr.addPanel(panel);

	}

	@PutMapping
	public void update(@RequestBody Panel panel) {
		pr.update(panel);

	}

}
