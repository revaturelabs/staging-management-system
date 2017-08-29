package com.revature.sms.controllers.rest;

import java.util.Collection;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.sms.entities.Associate;
import com.revature.sms.entities.AssociatesStatus;
import com.revature.sms.entities.Manager;
import com.revature.sms.entities.PortfolioStatus;
import com.revature.sms.entities.StaggingAssociate;
import com.revature.sms.services.AssociateService;
import com.revature.sms.services.TotalReport;
import com.revature.sms.services.TotalReport.TotalData;
import com.revature.sms.util.DataGeneration;

@RestController
@RequestMapping("associate")
public class AssociateControllerImpl {

	@Autowired
	private AssociateService associateService;
	@Autowired
	private DataGeneration dataGen;
	@Autowired
	TotalReport totalReport;
	@Value("${sms.salesforce}")
	private boolean salesforce;
	
	private static Logger logger = Logger.getRootLogger();

	private static final String LM = "login_manager";
	private static final String LA = "login_associate";

	public AssociateControllerImpl(AssociateService associateService) {
		super();
		this.associateService = associateService;
	}

	@PostMapping
	public ResponseEntity addAssociate(@RequestBody Associate associate, HttpSession session) {
		if (session.getAttribute(LM) == null) { // If you're not logged in as a
												// manger..
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
		}
		associateService.add(associate);
		return ResponseEntity.ok(null);
	}
     	@PutMapping("/portfolioStatus/{id}")
	public void updatePortfolio(@PathVariable long id, @RequestBody PortfolioStatus file)
	{
		Associate associate=associateService.getById(id);
	
		associate.setPortfolioStatus(file);
		associateService.add(associate);
	}

	@PostMapping("/add/all")
	public ResponseEntity addAssociates(@RequestBody Set<Associate> associates, HttpSession session) {
		if (session.getAttribute(LM) == null) { // If you're not logged in as a
												// manger..
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
		}
		for (Associate a : associates) {
			associateService.add(a);
		}
		return ResponseEntity.ok(null);
	}

	@GetMapping("/generate/mock-data")
	public ResponseEntity generateAssociateMockDate() {
		if(!salesforce)
		{
			dataGen.generate();
			return ResponseEntity.status(HttpStatus.OK).body(null);
		}
		else
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
	}

	@DeleteMapping
	public ResponseEntity deleteAssociate(@RequestBody Associate associate, HttpSession session) {
		if (session.getAttribute(LM) == null) { // If you're not logged in as a
												// manger..
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
		}
		associateService.delete(associate);
		return ResponseEntity.ok(null);
	}

	@PutMapping
	public ResponseEntity<Object> updateAssociate(@RequestBody Associate associate, HttpSession session) {
		Associate authenticatedAssociate = (Associate) session.getAttribute("login_associate");
		if (authenticatedAssociate != null) { // Associate edits their profile
			// Now we block any changes we don't want, by cherry picking the
			// associate information
			// from the passed in associate into the session associate.
			authenticatedAssociate.setSkills(associate.getSkills());
			authenticatedAssociate.setPortfolioLink(associate.getPortfolioLink());
			associateService.update(authenticatedAssociate);
			return ResponseEntity.ok(null);
		}
		Manager manager = (Manager) session.getAttribute(LM);
		if (manager != null) {// We trust managers. A lot.
			associate.setCredential(associateService.getById(associate.getId()).getCredential());
			associateService.update(associate);
			return ResponseEntity.ok(null);
		}

		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Associate> getAssociate(@PathVariable long id, HttpSession session) {
		Associate associate = (Associate) session.getAttribute(LA);
		if (session.getAttribute(LM) == null && (associate == null || associate.getId() != id)) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
		}
		return ResponseEntity.ok(associateService.getById(id));
	}

	@GetMapping("/all")
	public ResponseEntity<Set<Associate>> getAllAssociates(HttpSession session) {
		if (session.getAttribute(LM) == null) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
		}
		return ResponseEntity.ok(associateService.getAll());
	}

	@GetMapping("/allActive")
	public Set<Associate> getAllActiveAssociates(HttpSession session) {
		return associateService.getAllActive();
	}
	
	@GetMapping("/allTraining")
	public ResponseEntity<Set<Associate>> getAllActive(HttpSession session) {
//		if (session.getAttribute(LM) == null) {
//			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
//		}
		//AssociatesStatus stagingStatus = new AssociatesStatus(1, "STAGING");
		return ResponseEntity.ok(associateService.getAllByStatus("TRAINING"));
	}
	
	@GetMapping("/allStaging")
	public ResponseEntity<Set<Associate>> getAllInStaging(HttpSession session) {
		return ResponseEntity.ok(associateService.getAllByStatus("STAGING"));
	}
	
	@GetMapping("/allProject")
	public ResponseEntity<Set<Associate>> getAllInProject(HttpSession session) {
		return ResponseEntity.ok(associateService.getAllByStatus("PROJECT"));
	}
	
	@GetMapping("/allBench")
	public ResponseEntity<Set<Associate>> getAllInBench(HttpSession session) {
		return ResponseEntity.ok(associateService.getAllByStatus("BENCH"));
	}
	
	@GetMapping("no-batch")
	public Set<Associate> haveNoBatch() {
		return associateService.haveNoBatch();
	}
	
	@GetMapping("no-project")
	public Set<Associate> haveNoProject(){
		return associateService.haveNoProject();
	}

	@GetMapping("by-batch/{id}")
	public Set<Associate> byBatch(@PathVariable Long id) {
		return associateService.findByBatchId(id);
	}
	
	@GetMapping("by-project/{id}")
	public Set<Associate> byProject(@PathVariable Long id) {
		return associateService.findByProjectId(id);
	}

	@GetMapping(path = "/totaldata")
	public ResponseEntity<Collection<TotalData>> getAssocaites() {
		return ResponseEntity.ok(totalReport.process(associateService.getAllActive()));
	}
	
	@GetMapping(path = "/AssociatesInStaggin/{date}")
	public Set<StaggingAssociate> getAssociatesInStaggingOn(@PathVariable String date){
		logger.trace("DATE!!!!    " + date);
	  return associateService.getAssociatesInStaggingOn(date);
	}
	
	@GetMapping("/search/{searchName}")
	public Set<Associate> findByNameLike(@PathVariable String searchName){
		return associateService.findByNameLike(searchName);
	}
}
