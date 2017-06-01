package com.revature.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="JOBS")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Job {

	@Id
	@Column(name="JOB_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "JOB_ID_SEQ")
	@SequenceGenerator(name = "JOB_ID_SEQ", sequenceName = "JOB_ID_SEQ")

	private long id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ASSOCIATE_ID")
	private Associate associate;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="CLIENT_ID")
	private Client client;
	
	@Column(name="START_DATE")
	private Date startDate;
	
	@Column(name="PROJECTED_END_DATE")
	private Date projectedEndDate;
	
	@Column(name="ACTUAL_END_DATE")
	private Date endDate;
	
	@Column(name="BUYOUT_DATE")
	private Date buyoutDate;

	public Job(long id, Associate associate, Client clientId, Date startDate, Date projectedEndDate, Date endDate,
			   Date buyoutDate) {
		super();
		this.id = id;
		this.associate = associate;
		this.client = clientId;
		this.startDate = startDate;
		this.projectedEndDate = projectedEndDate;
		this.endDate = endDate;
		this.buyoutDate = buyoutDate;
	}

	public Job() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Associate getAssociate() {
		return associate;
	}

	public void setAssociate(Associate associate) {
		this.associate = associate;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getProjectedEndDate() {
		return projectedEndDate;
	}

	public void setProjectedEndDate(Date projectedEndDate) {
		this.projectedEndDate = projectedEndDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getBuyoutDate() {
		return buyoutDate;
	}

	public void setBuyoutDate(Date buyoutDate) {
		this.buyoutDate = buyoutDate;
	}


	@Override
	public String toString() {
		return "Job [id=" + id + ", associate=" + associate + ", client=" + client + ", startDate=" + startDate
				+ ", projectedEndDate=" + projectedEndDate + ", endDate=" + endDate + ", buyoutDate=" + buyoutDate
				+ "]";
	}
}
