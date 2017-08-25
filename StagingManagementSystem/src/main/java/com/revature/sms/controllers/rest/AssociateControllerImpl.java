package com.revature.sms.controllers.rest;

import java.util.Collection;
import java.util.Set;

import javax.servlet.http.HttpSession;

import com.revature.sms.entities.AssociatesStatus;
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
import com.revature.sms.entities.Manager;
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

//	we took this out
	@GetMapping("/allActive")
	public Set<Associate> getAllActiveAssociates(HttpSession session) {
	return associateService.getAllActive();
	}

	//getting all the assocoates by theit status
	//status is passing throuh the js file
	@GetMapping("{status}")
	public Set<Associate> getAllTraining(@PathVariable String status, HttpSession session)
	{
		return associateService.findByAssociateStatus(status);
	}

	//getting all the associates to display on the page by their status
	@GetMapping("/getAllInTraining")
	public Set<Associate> getAllTrainingAssociate(HttpSession session){
		return associateService.findByAssociateStatus("Training");
	}

	@GetMapping("/getAllInStaging")
	public Set<Associate> getAllStagingAssociate(HttpSession session){
		return associateService.findByAssociateStatus("Staging");
	}

	@GetMapping("/getAllInProject")
	public Set<Associate> getAllProjectAssociate(HttpSession session){
		return associateService.findByAssociateStatus("Project");
	}

	@GetMapping("/getAllInBench")
	public Set<Associate> getAllBenchAssociate(HttpSession session){
		return associateService.findByAssociateStatus("Bench");
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
	public ResponseEntity<Collection<TotalData>> getAssocaites()
	{
		return ResponseEntity.ok(totalReport.process(associateService.findByAssociateStatus("Staging")));
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
