package com.revature.sms.entities;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Table(name = "ASSOCIATES")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Associate {

	@Id
	@Column(name = "ASSOCIATE_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ASSOCIATE_ID_SEQ")
	@SequenceGenerator(name = "ASSOCIATE_ID_SEQ", sequenceName = "ASSOCIATE_ID_SEQ")
	private long id;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "CREDENTIAL_ID")
	private Credential credential;

	@Column(name = "ASSOCIATE_NAME")
	private String name;

	@Column(name = "ASSOCIATE_PORTFOLIO_LINK")
	private String portfolioLink;

	@ManyToOne
	@JoinColumn(name = "BATCH_ID")
	private Batch batch;

	@ManyToOne
	@JoinColumn(name = "CLIENT_ID")
	private Client lockedTo;
	
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="PORTFOLIO_STATUS_ID")
	private PortfolioStatus portfolioStatus;
	
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="ASSOCIATE_STATUS_ID")
	private AssociatesStatus associateStatus;
	

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "ASSOCIATE_SKILLS", joinColumns = @JoinColumn(name = "ASSOCIATE_ID"), inverseJoinColumns = @JoinColumn(name = "SKILL_ID"))
	private Set<Skill> skills;

	@OneToMany(mappedBy = "associate")
	private Set<Job> jobs;

	public Associate() {
		super();
		this.skills = new HashSet<>();
		this.jobs = new HashSet<>();
		this.associateStatus = new AssociatesStatus();
		this.portfolioStatus = new PortfolioStatus();
	}

	public Associate(long id, Credential credential, String name, String portfolioLink, Batch batch,
			Client lockedTo, PortfolioStatus portfolioStatusId, AssociatesStatus associateStatusId, Set<Skill> skills, Set<Job> jobs) 
	{
		super();
		this.id = id;
		this.credential = credential;
		this.name = name;
		this.portfolioLink = portfolioLink;
		this.batch = batch;
		this.lockedTo = lockedTo;
		this.portfolioStatus = portfolioStatusId;
		this.associateStatus = associateStatusId;
		this.skills = skills;
		this.jobs = jobs;
	}



	/**
	 *  Returns true if associate was on job during the given date.
	 */
	public boolean hasJobOnDate(LocalDateTime date){
	  for(Job j : jobs){
	    boolean beforeEnd = j.getEndDate() == null || date.compareTo(j.getEndDate()) < 0;
	    boolean hasentStopped = j.getEndDate() == null;
	    boolean afterStart = date.compareTo(j.getStartDate()) > 0;
	    
	    if(afterStart && (hasentStopped || beforeEnd))
	      return true;
	  }
	  return false;
	}
	
	/**
	 * Returns true if associate has not started their training and they have not had
	 * any jobs. Leaving it possible for associates to participate in multiple training
	 * batches only after they have had at least one job.
	 */
	public boolean hasStartedOnDate(LocalDateTime date) {
		boolean hasBegunTraining = date.compareTo(batch.getStartDate()) > 0;

		if(hasBegunTraining)
			     return true;
		return false;
	}
	
	/**
	 * Returns true if associate was in training during the given date.
	 */
	public boolean isTrainingOnDate(LocalDateTime adate) {
	   LocalDateTime date = adate.withHour(12); //Set mid day all other events should be the beginning of the day.
	   boolean afterBatchStart = date.compareTo(batch.getStartDate()) > 0;
	   boolean beforeBatchEnd = date.compareTo(batch.getEndDate()) < 0;
	   if(afterBatchStart && beforeBatchEnd)
	     return true;
	  
	  return false;
	}
	
	/**
	 * This function returns true if the associate was in staging on the given date.
	 */
	public boolean isTrackedOnDate(LocalDateTime date) {
	     if(hasStartedOnDate(date) && !isTrainingOnDate(date) && !hasJobOnDate(date))
	    	 return true;
	     return false;
	}
	
	/**
	 * This function returns true if the associate is in Staging and is available for hire
	 */
	public boolean isActive() {
		return (associateStatus.getStatus().equals("Staging")) ? true : false;
	}
	
	/**
	 * This function sets the associate status to staging if associate is in
	 */
	public void setStatus() {
		if(this.isTrainingOnDate(LocalDateTime.now()))
			associateStatus.setStatus("Training");
		
		if(this.isTrackedOnDate(LocalDateTime.now()))
			associateStatus.setStatus("Staging");
		
		if(this.hasJobOnDate(LocalDateTime.now()))
			associateStatus.setStatus("Project");
		
		if(!this.hasJobOnDate(LocalDateTime.now()))
			associateStatus.setStatus("Bench");
	}

	public long getId() 
	{
		return id;
	}

	public void setId(long id) 
	{
		this.id = id;
	}

	public Credential getCredential() 
	{
		return credential;
	}

	public void setCredential(Credential credential) 
	{
		this.credential = credential;
	}

	public String getName() 
	{
		return name;
	}

	public void setName(String name) 
	{
		this.name = name;
	}

	public String getPortfolioLink() 
	{
		return portfolioLink;
	}

	public void setPortfolioLink(String portfolioLink) 
	{
		this.portfolioLink = portfolioLink;
	}

	public Batch getBatch() 
	{
		return batch;
	}

	public void setBatch(Batch batch) 
	{
		this.batch = batch;
	}

	public Client getLockedTo() 
	{
		return lockedTo;
	}

	public void setLockedTo(Client lockedTo) 
	{
		this.lockedTo = lockedTo;
	}

	public PortfolioStatus getPortfolioStatus() 
	{
		return portfolioStatus;
	}

	public void setPortfolioStatus(PortfolioStatus portfolioStatus) 
	{
		this.portfolioStatus = portfolioStatus;
	}

	public AssociatesStatus getAssociateStatus() 
	{
		return associateStatus;
	}

	public void setAssociateStatus(AssociatesStatus associateStatus) 
	{
		this.associateStatus = associateStatus;
	}

	public Set<Skill> getSkills() 
	{
		return skills;
	}

	public void setSkills(Set<Skill> skills) {
		this.skills = skills;
	}

	public Set<Job> getJobs() {
		return jobs;
	}

	public void setJobs(Set<Job> jobs) {
		this.jobs = jobs;
	}


//	@Override
//	public String toString() {
//		return "Associate [id=" + id + ", credential=" + credential + ", name=" + name + ", portfolioLink="
//				+ portfolioLink + ", batch=" + batch + ", active=" + active + ", lockedTo=" + lockedTo
//				+ ", portfolioStatusId=" + portfolioStatusId + ", associateStatusId=" + associateStatusId + ", skills="
//				+ skills + "]";
//	}
}
