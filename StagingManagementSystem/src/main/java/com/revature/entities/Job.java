package com.revature.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="JOBS")
public class Job {

	@Id
	@Column(name="JOB_ID")

	private long id;
	
	@Column(name="ASSOCIATE_ID")
	private long associateId;
	
	@Column(name="CLIENT_ID")
	private long clientId;
	
	@Column(name="START_DATE")
	private Date startDate;
	
	@Column(name="PROJECTED_END_DATE")
	private Date projectedEndDate;
	
	@Column(name="ACTUAL_END_DATE")
	private Date endDate;
	
	@Column(name="BUYOUT_DATE")
	private Date buyoutDate;

	public Job(long id, long associateId, long clientId, Date startDate, Date projectedEndDate, Date endDate,
			Date buyoutDate) {
		super();
		this.id = id;
		this.associateId = associateId;
		this.clientId = clientId;
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

	public long getAssociateId() {
		return associateId;
	}

	public void setAssociateId(long associateId) {
		this.associateId = associateId;
	}

	public long getClientId() {
		return clientId;
	}

	public void setClientId(long clientId) {
		this.clientId = clientId;
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (associateId ^ (associateId >>> 32));
		result = prime * result + ((buyoutDate == null) ? 0 : buyoutDate.hashCode());
		result = prime * result + (int) (clientId ^ (clientId >>> 32));
		result = prime * result + ((endDate == null) ? 0 : endDate.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((projectedEndDate == null) ? 0 : projectedEndDate.hashCode());
		result = prime * result + ((startDate == null) ? 0 : startDate.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Job other = (Job) obj;
		if (associateId != other.associateId)
			return false;
		if (buyoutDate == null) {
			if (other.buyoutDate != null)
				return false;
		} else if (!buyoutDate.equals(other.buyoutDate))
			return false;
		if (clientId != other.clientId)
			return false;
		if (endDate == null) {
			if (other.endDate != null)
				return false;
		} else if (!endDate.equals(other.endDate))
			return false;
		if (id != other.id)
			return false;
		if (projectedEndDate == null) {
			if (other.projectedEndDate != null)
				return false;
		} else if (!projectedEndDate.equals(other.projectedEndDate))
			return false;
		if (startDate == null) {
			if (other.startDate != null)
				return false;
		} else if (!startDate.equals(other.startDate))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Job [id=" + id + ", associateId=" + associateId + ", clientId=" + clientId + ", startDate=" + startDate
				+ ", projectedEndDate=" + projectedEndDate + ", endDate=" + endDate + ", buyoutDate=" + buyoutDate
				+ "]";
	}
	
	
}
