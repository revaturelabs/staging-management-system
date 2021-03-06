package com.revature.sms.entities;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
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
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
@Table(name = "ASSOCIATES")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Associate {

	@Id
	@Column(name = "ASSOCIATE_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ASSOCIATE_ID_SEQ")
	@SequenceGenerator(name = "ASSOCIATE_ID_SEQ", sequenceName = "ASSOCIATE_ID_SEQ")
	private long id;

	@Column(name="SALESFORCE_ID")
    private String salesforceId;

    @OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "CREDENTIAL_ID")
	private Credential credential;
	
	@Column(name = "ASSOCIATE_NAME")
	private String name;

	@Column(name = "ASSOCIATE_PORTFOLIO_LINK")
	private String portfolioLink;

	@Column(name = "PANEL_STATUS")
	private String latestPanelStatus;
	
	@ManyToOne
	@JoinColumn(name = "BATCH_ID")
	private Batch batch;

	@ManyToOne
	@JoinColumn(name = "PROJECT_ID")
	private Project project;

	@ManyToOne
	@JoinColumn(name = "CLIENT_ID")
	private Client lockedTo;

	@Column(name = "PORTFOLIO_STATUS")
	private boolean portfolioStatus;

	//This may not the best, but otherwise hibernate will not be able to quick and easy save it
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="ASSOCIATE_STATUS")
	private AssociatesStatus associateStatus;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "ASSOCIATE_SKILLS", joinColumns = @JoinColumn(name = "ASSOCIATE_ID"), inverseJoinColumns = @JoinColumn(name = "SKILL_ID"))
	private Set<Skill> skills;
	
	@OneToMany(mappedBy = "associate")
	private Set<Job> jobs;
	
	//@OneToMany(mappedBy="associate")
	@OneToMany(mappedBy="associate",fetch = FetchType.LAZY)
	@JsonProperty(access = Access.WRITE_ONLY)
	private Set<Certifications> certifications;
	
	public Associate() {
		super();
		this.skills = new HashSet<>();
		this.jobs = new HashSet<>();
		this.certifications = new HashSet<>();
		this.associateStatus = new AssociatesStatus();
	}
	
	public Associate(long id, String salesforceId, Credential credential, String name, String portfolioLink, 
			String latestPanelStatus, Batch batch, Project project, Client lockedTo, boolean portfolioStatus,
			AssociatesStatus associateStatus, Set<Skill> skills, Set<Job> jobs, Set<Certifications> certifications) {
		super();
		this.id = id;
		this.salesforceId = salesforceId;
		this.credential = credential;
		this.name = name;
		this.portfolioLink = portfolioLink;
		this.latestPanelStatus = latestPanelStatus;
		this.batch = batch;
		this.project = project;
		this.lockedTo = lockedTo;
		this.portfolioStatus = portfolioStatus;
		this.associateStatus = associateStatus;
		this.skills = skills;
		this.jobs = jobs;	
		this.certifications=certifications;
	}

	public void checkAssociateStatus() {
		
		if (isTrackedOnDate(LocalDateTime.now())) {
			AssociatesStatus status = new AssociatesStatus(1, "STAGING");
			setAssociateStatus(status);
		}
		else if (this.hasJobOnDate(LocalDateTime.now())) {
			AssociatesStatus status = new AssociatesStatus(2, "PROJECT");
			setAssociateStatus(status);
		}
		else if (this.isTrackedOnDate(LocalDateTime.now()) && !this.hasJobOnDate(LocalDateTime.now())) {
			AssociatesStatus status = new AssociatesStatus(3, "BENCH");
			setAssociateStatus(status);
		}
		else {
			AssociatesStatus status = new AssociatesStatus(4, "TRAINING");
			setAssociateStatus(status);
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Associate other = (Associate) obj;
		if (associateStatus == null) {
			if (other.associateStatus != null)
				return false;
		} else if (!associateStatus.equals(other.associateStatus))
			return false;
		if (batch == null) {
			if (other.batch != null)
				return false;
		} else if (!batch.equals(other.batch))
			return false;
		if (certifications == null) {
			if (other.certifications != null)
				return false;
		} else if (!certifications.equals(other.certifications))
			return false;
		if (credential == null) {
			if (other.credential != null)
				return false;
		} else if (!credential.equals(other.credential))
			return false;
		if (id != other.id)
			return false;
		if (jobs == null) {
			if (other.jobs != null)
				return false;
		} else if (!jobs.equals(other.jobs))
			return false;
		if (latestPanelStatus == null) {
			if (other.latestPanelStatus != null)
				return false;
		} else if (!latestPanelStatus.equals(other.latestPanelStatus))
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
		if (portfolioStatus != other.portfolioStatus)
			return false;
		if (project == null) {
			if (other.project != null)
				return false;
		} else if (!project.equals(other.project))
			return false;
		if (salesforceId == null) {
			if (other.salesforceId != null)
				return false;
		} else if (!salesforceId.equals(other.salesforceId))
			return false;
		if (skills == null) {
			if (other.skills != null)
				return false;
		} else if (!skills.equals(other.skills))
			return false;
		return true;
	}

	public AssociatesStatus getAssociateStatus() {
		return associateStatus;
	}

	public Batch getBatch() {
		return batch;
	}

	public Set<Certifications> getCertifications() {
		return certifications;
	}
	
	public Credential getCredential() {
		return credential;
	}
	
	public long getId() 
	{
		return id;
	}

	public Set<Job> getJobs() {
		return jobs;
	}

	public String getLatestPanelStatus(){
		return latestPanelStatus;
	}

	public Client getLockedTo() {
		return lockedTo;
	}

	public String getName() {
		return name;
	}

	public String getPortfolioLink() {
		return portfolioLink;
	}

	public boolean getPortfolioStatus() {
		return portfolioStatus;
	}

	public Project getProject() {
		return project;
	}

	public String getSalesforceId() {
		return salesforceId;
	}

	public Set<Skill> getSkills() {
		return skills;
	}

	/**
	 * Returns true if associate was on job during the given date.
	 */
	public boolean hasJobOnDate(LocalDateTime date) {
		for (Job j : jobs) {
			boolean beforeEnd = j.getEndDate() == null || date.compareTo(j.getEndDate()) < 0;
			boolean hasentStopped = j.getEndDate() == null;
			boolean afterStart = date.compareTo(j.getStartDate()) > 0;
			if (afterStart && (hasentStopped || beforeEnd))
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
		return (hasBegunTraining) ? true : false;
	}
	
	/**
	 * This function returns true if the associate is in Staging and is available for hire
	 */
	public boolean isActive() {
		return (associateStatus.getStatus().equals("STAGING") || associateStatus.getStatus().equals("BENCH"));
	}
	
	public boolean isBenched() {
		return associateStatus.getStatus().equals("BENCH") ? true : false;
	}

	/**
	 * This function returns true if the associate was in staging on the given date.
	 */
	public boolean isTrackedOnDate(LocalDateTime date) {
		return (hasStartedOnDate(date) && !isTrainingOnDate(date) && !hasJobOnDate(date)) ? true : false;
	}

	/**
	 * Returns true if associate was in training during the given date.
	 */
	public boolean isTrainingOnDate(LocalDateTime adate) {
		LocalDateTime date = adate.withHour(12); //Set mid day all other events should be the beginning of the day.
		boolean afterBatchStart = date.compareTo(batch.getStartDate()) > 0;
		boolean beforeBatchEnd = date.compareTo(batch.getEndDate()) < 0;
		return (afterBatchStart && beforeBatchEnd) ? true : false;
	}

	public void setAssociateStatus(AssociatesStatus associateStatus) {
		this.associateStatus = associateStatus;
	}

	public void setBatch(Batch batch) {
		this.batch = batch;
	}

	public void setCertifications(Set<Certifications> certifications) {
		this.certifications = certifications;
	}

	public void setCredential(Credential credential) {
		this.credential = credential;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setJobs(Set<Job> jobs) {
		this.jobs = jobs;
	}

	public void setLastestPanelStatus(String latestPanelStatus){
		this.latestPanelStatus = latestPanelStatus;
	}

	public void setLockedTo(Client lockedTo) {
		this.lockedTo = lockedTo;
	}
	
	public void setMockStatus() {
		checkAssociateStatus();
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPortfolioLink(String portfolioLink) {
		this.portfolioLink = portfolioLink;
	}

	public void setPortfolioStatus() {
		if(associateStatus.getStatus().equals("STAGING"))
			portfolioStatus = true;
		else {
			portfolioStatus = false;
		}
	}

	public void setProject(Project project) {
		this.project = project;
	}
	

	public void setSalesforceId(String salesforceId) {
		this.salesforceId = salesforceId;
	}

	public void setSkills(Set<Skill> skills) {
		this.skills = skills;
	}

/*	@Override
	public String toString() {
		return "Associate [id=" + id + ", salesforceId=" + salesforceId + ", credential=" + credential + ", name="
				+ name + ", portfolioLink=" + portfolioLink + ", latestPanelStatus=" + latestPanelStatus + ", batch="
				+ batch + ", project=" + project + ", lockedTo=" + lockedTo + ", portfolioStatus=" + portfolioStatus
				+ ", associateStatus=" + associateStatus + ", skills=" + skills + ", jobs=" + jobs + ", certifications="
				+ certifications + "]";
	}*/

	
}
