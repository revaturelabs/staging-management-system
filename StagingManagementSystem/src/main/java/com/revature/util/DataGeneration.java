package com.revature.util;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Random;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.entities.Associate;
import com.revature.entities.Checkin;
import com.revature.entities.Client;
import com.revature.entities.ClientQuestion;
import com.revature.entities.Interview;
import com.revature.entities.InterviewQuestion;
import com.revature.entities.InterviewStatuses;
import com.revature.entities.Job;
import com.revature.entities.Manager;
import com.revature.services.AssociateService;
import com.revature.services.BatchService;
import com.revature.services.CheckinService;
import com.revature.services.ClientQService;
import com.revature.services.ClientService;
import com.revature.services.InterviewQuestionService;
import com.revature.services.InterviewStatusService;
import com.revature.services.InterviewsService;
import com.revature.services.JobService;
import com.revature.services.ManagerService;


@Service
public class DataGeneration
{
  
  @Autowired
  CheckinService checkinService;
  @Autowired 
  ClientQService clientQService;
  @Autowired
  InterviewsService interviewsService;
  @Autowired
  InterviewStatusService interviewStatusService;
  @Autowired
  InterviewQuestionService interviewQuestionService;
  @Autowired
  BatchService batchService;
  @Autowired
  JobService jobService;
  @Autowired
  ClientService clientService;
  @Autowired
  ManagerService managerService;
  @Autowired
  AssociateService associateService;

  
	//Dependent Stages
	ArrayList<Checkin> checkins = new ArrayList<Checkin>();
	ArrayList<ClientQuestion> clientQs = new ArrayList<ClientQuestion>();	
	ArrayList<Interview> interviews = new ArrayList<Interview>();
	ArrayList<Job> jobs = new ArrayList<Job>();
	
	ArrayList<ClientP> priorityClients = new ArrayList<ClientP>();
	ArrayList<ClientP> regularClients = new ArrayList<ClientP>();
	ArrayList<Associate> associates = new ArrayList<Associate>();
	ArrayList<InterviewQuestion> interviewQuestions = new ArrayList<InterviewQuestion>();
	ArrayList<Manager> managers = new ArrayList<Manager>();
	
	
	String[] jobTitles = new String[]{"Janitor", "DishWasher"};
	
	Random rand = new Random();
	
	double probabilityOfPriorityInterview = 60;
	double probabilityOfRegularInterview = 30;
	
	Logger log = Logger.getRootLogger();
	
	/**
	 * A client object with hiring probabilities attached.
	 * 
	 * @author jozse
	 *
	 */
	class ClientP extends Client{
	  double probabilityOfHiring;
	  double probabilityOfLiking;
	  double probabilityOfNotInterested;
	  
	  
	  ClientP(Client c){
	    super(c.getId(), c.getName(), c.getPriority(), c.getActive());
	    
	    probabilityOfHiring = rand.nextInt(20) + 60;   //Hiring probability is between 60 and 80.
	    probabilityOfLiking = rand.nextInt(10);          //Liking probability is between 0 and 10.
	    probabilityOfNotInterested = 100 - (probabilityOfHiring + probabilityOfLiking);
	    
	    log.debug("Client probibility hiring/liking/interested: " + probabilityOfHiring + "/" + probabilityOfLiking + "/" + probabilityOfNotInterested);
	  }
	  
	   /**
     * Spring does not know about this class so this creates a super class to avoid errors.
     * @return - super instance
     */
	  Client getClient(){
	    return new Client(this.getId(), this.getName(), this.getPriority(), this.getActive());
	  }
	  
	  /**
	   *  Gives based on the hiring probability of this client and the clientProbabilityMultiplier or the associate,
	   *  determines if associate is hired liked or confirmed.
	   * @param a - associate to be evaluated
	   * @return - interviewStatus based on hiring probabilities.
	   */
	  InterviewStatuses evaluateAssociate(AssociateP a){
	    int rollDice = rand.nextInt(100);
	    
	    if(rollDice < probabilityOfHiring * a.clientProbabilityMultiplier)
	      return interviewStatusService.findByStatus("CONFIRMED");
	    
	    if(rollDice > 100 - (probabilityOfLiking * a.clientProbabilityMultiplier))
	      return interviewStatusService.findByStatus("LIKED");
	    
	    return interviewStatusService.findByStatus("NOT_INTERESTED");
	  }
	}
	
	
	/**
	 * An associate object probabilities attached.
	 * @author jozse
	 *
	 */
	class AssociateP extends Associate{
	  double clientProbabilityMultiplier;
	  
	  AssociateP(Associate a){
	    super(a.getId(), a.getCredential(), a.getName(), a.getPortfolioLink(), a.getBatch(), a.getActive(), a.getLockedTo(), null);
	    int qualityOfAssociate = rand.nextInt(100); 
	    
	    if(qualityOfAssociate < 20)    //20 percent chance of being half as hirable as the average associate.
	      clientProbabilityMultiplier = .5;  
	    else if (qualityOfAssociate > 99)  //1 percent chance of being one eight as hirable as the average associate.
	      clientProbabilityMultiplier = .125;
	    else
	      clientProbabilityMultiplier = 1; //The average associate corresponds with the client probabilities.
	    
	    log.debug("Associate ClientProbabilityMultiplier: " + clientProbabilityMultiplier);
	  }
	
	  /**
	   * Spring does not know about this class so this creates a super class to avoid errors.
	   * @return - super instance
	   */
	  Associate getAssocaite(){
	    return new Associate(getId(), getCredential(), getName(), getPortfolioLink(), getBatch(), getActive(), getLockedTo(), null);
	  }
	}

	
	public void generate(){	  
    associates.addAll(associateService.getAll());
    interviewQuestions.addAll(interviewQuestionService.getAll());
    managers.addAll(managerService.getAll());

    Set<Client> allClients = clientService.getAll();
    for(Client c : allClients){
      if(c.getPriority())
        priorityClients.add(new ClientP(c));
      else
        regularClients.add(new ClientP(c));
    }

	  for(Associate a : associates){
      AssociateP ap = new AssociateP(a); //Create a probability associate.
	    if(!ap.getActive())
	      ap.setActive(true); // When generating data associates should be active

	    LocalDateTime endDate = a.getBatch().getEndDate();
	    LocalDateTime currDate = endDate.minusDays(7); //Hiring date is from a week before batch end date to confirmed date. 
	    LocalDateTime confirmDate = null;
	    
	    while(confirmDate == null && currDate.compareTo(LocalDateTime.now()) < 0){
	      if(currDate.compareTo(endDate.plusMonths(5)) > 0){ //If associate does not get hired after 5 months.
	        log.warn("Associate didint get a job in 5 months!!!");
	        ap.setActive(false);
	        break;
	      }
	      
	      log.debug("Current Date: " + currDate + "\tAssociate name: " + ap.getName());
	      
	      int nextPossibleInterview = rand.nextInt(10) + 2; //next Possible date i between 2 and 12 days away averaging 1 a week.
	      currDate = currDate.plusDays(nextPossibleInterview);
	      log.debug("Interview gap/adjustedDate: " + nextPossibleInterview + "/" + currDate);
	      
	      // Determines if on this currDate a priority Interview is scheduled.
	      int rollDiceInterview = rand.nextInt(100); 
        //Halve the probability if it is before batch endDate.
	      double probabilityOfInterview = probabilityOfPriorityInterview * ap.clientProbabilityMultiplier * (0 < currDate.compareTo(endDate)  ? .5 : 1.0);
	      boolean interview = rollDiceInterview < probabilityOfInterview;
	      log.debug("priority interview diceRoll/probabilityOfInterview/boolean: " + rollDiceInterview + "/" + probabilityOfInterview + "/" + interview);
	      
	      // If client has priority interview simulate process for that interview, else roll the dice for regular client interview.
	      if(interview){
	        // For priority clients revature awaits their decision before more interviews.
	        int daysToDecide = logRythmicConvergence(0, 7, .5);
	        currDate = currDate.plusDays(daysToDecide);
	        log.debug("Priority Client decision days days/date: " + daysToDecide + "/" + currDate);
	        
	        
	        int clientIndex = logRythmicConvergence(0, priorityClients.size(), .6);
	        ClientP client = priorityClients.get(clientIndex);
	        InterviewStatuses is = client.evaluateAssociate(ap);
	        log.debug("Client Decision: " + is);
	        
	        //Save Interview
	        Interview i = new Interview(null, ap, client, is, currDate);
	        interviewsService.add(i);
	        
	        submitInterviewQuestions(ap, client);
	        
	        // Create Job.
	        if(is.getValue().equals("CONFIRMED")){
	          
            LocalDateTime startDate = currDate.plusWeeks(2);
            confirmDate = createJob(ap, currDate, startDate, client);
            
            createCheckins(endDate, startDate, ap);
	        }
	      }
	      else 
	      {
	        // Determines if on this currDate a regular Interview is scheduled.
	        rollDiceInterview = rand.nextInt(100); 
	        //Halve the probability if it is before batch endDate.
	        probabilityOfInterview = probabilityOfPriorityInterview * ap.clientProbabilityMultiplier * (0 < currDate.compareTo(endDate)  ? .5 : 1.0);
	        interview = rollDiceInterview < probabilityOfInterview;
	        log.debug("regular interview diceRoll/probabilityOfInterview/boolean: " + rollDiceInterview + "/" + probabilityOfInterview + "/" + interview);

	        if(interview){
	          
	          int clientIndex = logRythmicConvergence(0, regularClients.size(), .3);
	          ClientP client = regularClients.get(clientIndex);
	          InterviewStatuses is = client.evaluateAssociate(ap);
	          
	          //Save Interview
	          Interview i = new Interview(null, ap, client, is, currDate);
	          interviewsService.add(i);
	          
	          submitInterviewQuestions(ap, client);
	          
	          // Create Job.
	          if(is.getValue().equals("CONFIRMED")){
	            
	             int daysToDecide = logRythmicConvergence(0, 7, .5);
	             currDate = currDate.plusDays(daysToDecide);

	             LocalDateTime startDate = currDate.plusWeeks(2);
	             confirmDate = createJob(ap, currDate, startDate, client);
	             
	             createCheckins(endDate, startDate, ap);
	          }
	        }
	      }
	    }

	    associateService.update(ap.getAssocaite());

	  }
	 }

  private LocalDateTime createJob(AssociateP ap, LocalDateTime currDate, LocalDateTime startDate, ClientP client) {
    // projectedEndDate and EndDate are the same for more realistic data randomize end date and buyoutDate.

    LocalDateTime projectedEndDate = currDate.plusYears(rand.nextBoolean() ? 1 : 2);
    LocalDateTime confirmDate = currDate;
    //Should randomize actual endDate by creating a bias in the client.
    
    Job j = new Job(0l, ap, client, startDate, projectedEndDate,
        projectedEndDate, null, confirmDate);
    jobService.add(j);
    log.debug("Created Job: " + j);
    
    ap.setLockedTo(client);
    
    return confirmDate;
  }
	
  /**
   * Checkin creation between the batch endDate and the startDate.
   * 
   * @param batchEndDate - date marking the end of training.
   * @param startDate - date marking the start of client employment.
   * @param associate - associate that is checking in.
   */
	private void createCheckins(LocalDateTime batchEndDate, LocalDateTime startDate, Associate associate){
	  LocalDateTime currDate = batchEndDate;
	  while(currDate.compareTo(startDate) < 0){
	    currDate = currDate.plusDays(1);
	    int managerIndex = rand.nextInt(managers.size());
	    
	    Checkin checkin = new Checkin(0l, currDate.withHour(8), currDate.withHour(16), managers.get(managerIndex),
	        currDate.withHour(10), associate);
	    
	    checkinService.add(checkin);
	    log.debug("Created checkin: " + checkin);
	  }
	}

  private void submitInterviewQuestions(AssociateP ap, ClientP client) {
    //Allow associate to submit questions
    int probAssociateSubQs = rand.nextInt(5);  // one in 5 interviews has questions posted.
    if(probAssociateSubQs == 3){
      int numberOfQuestions = rand.nextInt(4);   // between 1 and 4 questions are submitted.
      ArrayList<Integer> chosenQuestions = new ArrayList<Integer>();
      while(chosenQuestions.size() < numberOfQuestions){
        Integer qIndex = rand.nextInt(interviewQuestions.size());
        if(!chosenQuestions.contains(qIndex)){
          chosenQuestions.add(qIndex);
          InterviewQuestion iq = interviewQuestions.get(qIndex);
          clientQService.add(new ClientQuestion(null, client, iq, ap));
          log.debug("Question at index " + qIndex + ": " + iq);
        }
      }
    }
  }
	
	private int logRythmicConvergence(int start, int end, double convergenceFactor){
	  double totalProbability = convergenceFactor;
	  while(totalProbability < 1){
	    start++;
	    int rollDice = rand.nextInt(100);
	    
	    log.debug("Accept if: " + totalProbability*100 + " > " + rollDice);
	    if(totalProbability * 100 > rollDice)
	      return start % end;
	    
	    totalProbability += (1 - totalProbability)*convergenceFactor;
	  }
	  log.warn("Convergence did not yeald result, This should never happen.");
	  return start % end;
	}
}
