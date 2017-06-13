package com.revature.entities;

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
import com.revature.config.SmsSettings;
import com.revature.exceptions.SmsCustomException;
import com.revature.markers.SmsValidatable;

@Entity
@Table(name = "ASSOCIATES")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Associate implements SmsValidatable {

	transient private static SmsSettings settings = SmsSettings.getInstance();

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

	@Column(name = "ASSOCIATE_ACTIVE")
	private boolean active;

	@ManyToOne
	@JoinColumn(name = "CLIENT_ID")
	private Client lockedTo;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "ASSOCIATE_SKILLS", joinColumns = @JoinColumn(name = "ASSOCIATE_ID"), inverseJoinColumns = @JoinColumn(name = "SKILL_ID"))
	private Set<Skill> skills;

	@OneToMany(mappedBy = "associate")
	private Set<Job> jobs;

	public Associate() {
		super();
		this.skills = new HashSet<Skill>();
		this.jobs = new HashSet<Job>();
		this.active = true;
	}

	public Associate(long id, Credential credential, String name, String portfolioLink, Batch batch, boolean active,
			Client lockedTo, Set<Skill> skills, Set<Job> jobs) {
		super();
		this.id = id;
		this.credential = credential;
		this.name = name;
		this.portfolioLink = portfolioLink;
		this.batch = batch;
		this.active = active;
		this.lockedTo = lockedTo;
		this.skills = skills;
		this.jobs = jobs;
	}
	
	/**
	 *  Returns true if associate was on job during the given date.
	 */
	public boolean hasJobOnDate(LocalDateTime date){
	  date = date.withHour(12); //Set mid day all other events should be the beginning of the day.
	  for(Job j : jobs){
	    boolean beforeEnd = date.compareTo(j.getEndDate()) < 0;
	    boolean hasentStopped = j.getEndDate() == null;
	    boolean afterStart = date.compareTo(j.getStartDate()) > 0;

	    if(afterStart && (hasentStopped || beforeEnd))
	      return true;
	  }
	  return false;
	}
	
	/**
	 * Returns true if associate has started thier training and they have not had
	 * any jobs. Leaving it possible for associates to participate in multiple training
	 * batches only after they have had atleast one job.
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
	public boolean isTrainingOnDate(LocalDateTime date) {
	   date = date.withHour(12); //Set mid day all other events should be the beginning of the day.
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

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Credential getCredential() {
		return credential;
	}

	public void setCredential(Credential credential) {
		this.credential = credential;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPortfolioLink() {
		return portfolioLink;
	}

	public void setPortfolioLink(String portfolioLink) {
		this.portfolioLink = portfolioLink;
	}

	public Batch getBatch() {
		return batch;
	}

	public void setBatch(Batch batch) {
		this.batch = batch;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive() {
		if(this.isTrackedOnDate(LocalDateTime.now()))
			this.active = true;
		else
			this.active = false;
	}

	public Client getLockedTo() {
		return lockedTo;
	}

	public void setLockedTo(Client lockedTo) {
		this.lockedTo = lockedTo;
	}

	public Set<Skill> getSkills() {
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (active ? 1231 : 1237);
		result = prime * result + ((credential == null) ? 0 : credential.hashCode());
		result = prime * result + ((lockedTo == null) ? 0 : lockedTo.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((portfolioLink == null) ? 0 : portfolioLink.hashCode());
		result = prime * result + ((skills == null) ? 0 : skills.hashCode());
		return result;
	}

	@Override
	final public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Associate))
			return false;
		Associate other = (Associate) obj;
		if (active != other.active)
			return false;
		if (credential == null) {
			if (other.credential != null)
				return false;
		} else if (!credential.equals(other.credential))
			return false;
		if (lockedTo == null) {
			if (other.lockedTo != null)
				return false;
		} else if (!lockedTo.equals(other.lockedTo))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (portfolioLink == null) {
			if (other.portfolioLink != null)
				return false;
		} else if (!portfolioLink.equals(other.portfolioLink))
			return false;
		if (skills == null) {
			if (other.skills != null)
				return false;
		} else if (!skills.equals(other.skills))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Associate [id=" + id + ", credential=" + credential + ", name=" + name + ", portfolioLink="
				+ portfolioLink + ", batch=" + (batch == null ? null : batch.getBatchType().getValue()) + ", active=" + active + ", lockedTo="
				+ lockedTo + ", skills=" + skills + "]";
	}

	@Override
	public void validate() throws SmsCustomException {
		// TODO Validate your members.
	}

}
