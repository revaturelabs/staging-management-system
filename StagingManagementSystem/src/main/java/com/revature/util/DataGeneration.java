package com.revature.util;

import java.util.ArrayList;

import com.revature.entities.Associate;
import com.revature.entities.Batch;
import com.revature.entities.BatchType;
import com.revature.entities.Check;
import com.revature.entities.Client;
import com.revature.entities.Credential;
import com.revature.entities.InterviewQuestion;
import com.revature.entities.InterviewStatuses;
import com.revature.entities.Interview;
import com.revature.entities.Job;
import com.revature.entities.Location;
import com.revature.entities.Manager;
import com.revature.entities.Permission;
import com.revature.entities.Trainer;

public class DataGeneration
{
	//Stage 1: Dependent Lower Stages
	ArrayList<Location> locations = new ArrayList<Location>();
	ArrayList<Permission> permissions = new ArrayList<Permission>();
	ArrayList<Trainer> trainers = new ArrayList<Trainer>();
	ArrayList<InterviewStatuses> interviewStatuses = new ArrayList<InterviewStatuses>();
	ArrayList<Credential> credentials = new ArrayList<Credential>();
	ArrayList<BatchType> batchTypes = new ArrayList<BatchType>();
	ArrayList<Client> clients = new ArrayList<Client>();

	
	//Stage 2: Dependent Lower Stages
	ArrayList<Batch> batchs = new ArrayList<Batch>();
	ArrayList<InterviewQuestion> interviewQuestions = new ArrayList<InterviewQuestion>();
	ArrayList<Manager> managers = new ArrayList<Manager>();

	
	//Stage 3: Dependent Lower Stages
	ArrayList<Associate> associate = new ArrayList<Associate>();

	//Stage 4: Dependent Lower Stages
	ArrayList<Check> checkins = new ArrayList<Check>();
	//ArrayList<ClientQ> clientQs = new ArrayList<ClientQ>();	
	ArrayList<Interview> interviews = new ArrayList<Interview>();
	ArrayList<Job> jobs = new ArrayList<Job>();
	
	public void generate(){
		stage1();
		stage2();
		stage3();
		stage4();
	}
	
	public void stage1(){
		//TODO: generate stage 1
	}
	
	public void stage2(){
		//TODO: generate stage 2
	}
	
	public void stage3(){
		//TODO: generate stage 3
	}
	
	public void stage4(){
		//TODO: generate stage 4
	}
	
	//TODO: write creation functions within get methods.
	public ArrayList<Location> getLocations()
	{
		//TODO: write creation function.
		return locations;
	}
	public ArrayList<Permission> getPermissions()
	{
		//TODO: write creation function.
		return permissions;
	}
	public ArrayList<Trainer> getTrainers()
	{
		//TODO: write creation function.
		return trainers;
	}
	public ArrayList<InterviewStatuses> getInterviewStatuses()
	{
		//TODO: write creation function.
		return interviewStatuses;
	}
	public ArrayList<Credential> getCredentials()
	{
		//TODO: write creation function.
		return credentials;
	}
	public ArrayList<BatchType> getBatchTypes()
	{
		//TODO: write creation function.
		return batchTypes;
	}
	public ArrayList<Client> getClients()
	{
		//TODO: write creation function.
		return clients;
	}
	public ArrayList<Batch> getBatchs()
	{
		//TODO: write creation function.
		return batchs;
	}
	public ArrayList<InterviewQuestion> getInterviewQuestions()
	{
		//TODO: write creation function.
		return interviewQuestions;
	}
	public ArrayList<Manager> getManagers()
	{
		//TODO: write creation function.
		return managers;
	}
	public ArrayList<Associate> getAssociate()
	{
		//TODO: write creation function.
		return associate;
	}
	public ArrayList<Check> getCheckins()
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
}
