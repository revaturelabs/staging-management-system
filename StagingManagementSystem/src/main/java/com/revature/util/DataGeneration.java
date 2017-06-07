package com.revature.util;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Random;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import com.revature.entities.Associate;
import com.revature.entities.Checkin;
import com.revature.entities.Client;
import com.revature.entities.ClientQuestion;
import com.revature.entities.Interview;
import com.revature.entities.InterviewQuestion;
import com.revature.entities.InterviewStatuses;
import com.revature.entities.Job;
import com.revature.services.AssociateService;
import com.revature.services.BatchService;
import com.revature.services.CheckinService;
import com.revature.services.ClientQService;
import com.revature.services.ClientService;
import com.revature.services.InterviewsService;
import com.revature.services.JobService;

public class DataGeneration
{
  
  @Autowired
  CheckinService checkinService;
  @Autowired 
  ClientQService clientQService;
  @Autowired
  InterviewsService interviewsService;
  @Autowired
  BatchService batchService;
  @Autowired
  JobService jobService;
  @Autowired
  ClientService clientService;
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
	
	String[] jobTitles = new String[]{"Janitor", "DishWasher"};
	
	Random rand = new Random();
	
	double probabilityOfPriorityInterview = 60;
	double probabilityOfRegularInterview = 30;
	
	class ClientP extends Client{
	  double probabilityOfHiring;
	  double probabilityOfLiking;
	  double probabilityOfNotInterested;
	  
	  public ClientP(Client c){
	    super(c);
	    
	    probabilityOfHiring = rand.nextInt(20) + 60;   //Hiring probability is between 60 and 80.
	    probabilityOfLiking = rand.nextInt(10);          //Liking probability is between 0 and 10.
	    probabilityOfNotInterested = 100 - (probabilityOfHiring + probabilityOfLiking);
	  }
	  
	  InterviewStatuses evaluateAssociate(AssociateP a){
	    int rollDice = rand.nextInt(100);
	    
	    if(rollDice < probabilityOfHiring * a.clientProbabilityMultiplier)
	      return new InterviewStatuses(51l, "CONFIRMED");
	    
	    if(rollDice > probabilityOfLiking * a.clientProbabilityMultiplier)
	      return new InterviewStatuses(54l, "LIKED");
	    
	    return new InterviewStatuses(50l, "NOT_INTERESTED");
	  }
	}
	
	class AssociateP extends Associate{
	  double clientProbabilityMultiplier;
	  
	  public AssociateP(Associate a){
	    super(a);
	    int qualityOfAssociate = rand.nextInt(100); 
	    
	    if(qualityOfAssociate < 10)
	      clientProbabilityMultiplier = .5;
	    else if (qualityOfAssociate > 99)
	      clientProbabilityMultiplier = .125;
	    else
	      clientProbabilityMultiplier = 1;
	  }
	}
	
	public DataGeneration(){
	   associates.addAll(associateService.getAll());
	   Set<Client> allClients = clientService.getAll();
	   for(Client c : allClients){
	     if(c.getPriority())
	       priorityClients.add(new ClientP(c));
	     else
	       regularClients.add(new ClientP(c));
	   }
	   generate();
	}
	
	public void generate(){	  
	  for(Associate a : associates){
      AssociateP ap = new AssociateP(a); //Create a probability associate.
	    if(!ap.getActive())
	      ap.setActive(true); // When generating data associates should be active
	    
	    LocalDateTime endDate = a.getBatch().getEndDate();
	    LocalDateTime currDate = endDate.minusDays(7); //Hiring date is from a week before batch end date to confirmed date. 
	    LocalDateTime confirmDate = null;
	    
	    while(confirmDate == null){
	      if(currDate.compareTo(endDate.plusMonths(5)) > 0){ //If associate does not get hired after 5 months.
	        ap.setActive(false);
	        associateService.update(ap);
	        break;
	      }
	      
	      int nextPossibleInterview = rand.nextInt(10) + 2; //next Possible date i between 2 and 12 days away averaging 1 a week.
	      currDate.plusDays(nextPossibleInterview);
	      
	      // Determines if on this currDate a priority Interview is scheduled.
	      int rollDiceInterview = rand.nextInt(100); 
	      double probabilityOfInterview = probabilityOfPriorityInterview * ap.clientProbabilityMultiplier * (0 < currDate.compareTo(endDate)  ? .5 : 1.0);
	      boolean interview = rollDiceInterview < probabilityOfInterview;
	      
	      if(interview){
	        // For priority clients revature awaits their decision before more interviews.
	        int daysToDecide = logRythmicConvergence(0, 7, .5);
	        currDate.plusDays(daysToDecide);
	        
	        
	        int clientIndex = logRythmicConvergence(0, -1, .3);
	        ClientP client = priorityClients.get(clientIndex);
	        InterviewStatuses is = client.evaluateAssociate(ap);
	        
	        //Save Interview
	        Interview i = new Interview(null, ap, client, is, currDate);
	        interviewsService.add(i);
	        
	        submitInterviewQuestions(ap, client);
	        
	        // Create Job.
	        if(is.getValue().equals("CONFIRMED")){
	          
	          // projectedEndDate and EndDate are the same for more realistic data randomize end date and buyoutDate.
	          LocalDateTime projectedEndDate = currDate.plusYears(rand.nextBoolean() ? 1 : 2);
	          Job j = new Job(null, ap, client, currDate, projectedEndDate,
	              projectedEndDate, null, currDate);
            jobService.add(j);
	        }
	      }
	      else 
	      {
	        // Determines if on this currDate a regular Interview is scheduled.
	        rollDiceInterview = rand.nextInt(100); 
	        probabilityOfInterview = probabilityOfPriorityInterview * ap.clientProbabilityMultiplier * (0 < currDate.compareTo(endDate)  ? .5 : 1.0);
	        interview = rollDiceInterview < probabilityOfInterview;
	        
	        if(interview){
	          
	          int clientIndex = logRythmicConvergence(0, -1, .3);
	          ClientP client = priorityClients.get(clientIndex);
	          InterviewStatuses is = client.evaluateAssociate(ap);
	          
	          //Save Interview
	          Interview i = new Interview(null, ap, client, is, currDate);
	          interviewsService.add(i);
	          
	          submitInterviewQuestions(ap, client);
	          
	          // Create Job.
	          if(is.getValue().equals("CONFIRMED")){
	            
	            // projectedEndDate and EndDate are the same for more realistic data randomize end date and buyoutDate.
	            LocalDateTime projectedEndDate = currDate.plusYears(rand.nextBoolean() ? 1 : 2);
	            int daysToDecide = logRythmicConvergence(0, 7, .5);
	            currDate.plusDays(daysToDecide);
	            Job j = new Job(null, ap, client, currDate, projectedEndDate,
	                projectedEndDate, null, currDate);
	            jobService.add(j);
	          }
	        }
	      }
	    }
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
          clientQService.add(new ClientQuestion(null, client, interviewQuestions.get(qIndex), ap));
        }
      }
    }
  }
	
	private int logRythmicConvergence(int start, int end, double convergenceFactor){
	  double totalProbability = convergenceFactor;
	  while(totalProbability < 1 && start < end){
	    start++;
	    int rollDice = rand.nextInt(100);
	    if(totalProbability * 100 < rollDice)
	      return start;
	  }
	  return start;
	}
	

	public ArrayList<Checkin> getCheckins()
	{
		return checkins;
	}
	public ArrayList<Interview> getInterviews()
	{
		return interviews;
	}
	public ArrayList<Job> getJobs()
	{
		return jobs;
	}
  public ArrayList<ClientQuestion> getClientQs() {
    return clientQs;
  }
}
