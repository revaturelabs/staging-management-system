package com.revature.util;

import java.util.ArrayList;
import java.util.Random;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import com.revature.entities.Associate;
import com.revature.entities.Checkin;
import com.revature.entities.Client;
import com.revature.entities.ClientQuestion;
import com.revature.entities.Interview;
import com.revature.entities.Job;
import com.revature.services.AssociateService;
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
	
	ArrayList<Client> priorityClients = new ArrayList<Client>();
	ArrayList<Client> regularClients = new ArrayList<Client>();
	ArrayList<Associate> associates = new ArrayList<Associate>();
	
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
	       priorityClients.add(c);
	     else
	       regularClients.add(c);
	   }
	   generate();
	}
	
	public void generate(){
	  for(Associate a : associates){
	    if(!a.getActive())      //
	      a.setActive(true);
	  }
	  
	}
	

	public ArrayList<Checkin> getCheckins()
	{
		//TODO: write creation function.
		return checkins;
	}
	public ArrayList<Interview> getInterviews()
	{
		//TODO: write creation function.
		return interviews;
	}
	public ArrayList<Job> getJobs()
	{
		//TODO: write creation function.
		return jobs;
	}
  public ArrayList<ClientQuestion> getClientQs() {
    return clientQs;
  }
}
