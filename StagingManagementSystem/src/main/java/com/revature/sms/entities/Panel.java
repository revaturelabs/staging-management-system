package com.revature.sms.entities;

import java.sql.Date;

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

@Entity
@Table(name = "PANEL")
public class Panel 
{
	@Id
	@Column(name = "PANEL_ID")
	@SequenceGenerator(name = "PANEL_ID_SEQ", sequenceName = "PANEL_ID_SEQ",allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PANEL_ID_SEQ")
	
	private long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ASSOCIATE_ID")
	private Associate associate;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="MANAGER_ID")
	private Manager SMS;
	
	
	private String comments;
	
	@Column(name="STATUS_DATE ")
	private Date statusDate;
	
	@Column(name="STATUS")
	private String panelStatus;

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

	public Manager getSMS() {
		return SMS;
	}

	public void setSMS(Manager sMS) {
		SMS = sMS;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Date getStatusDate() {
		return statusDate;
	}

	public void setStatusDate(Date statusDate) {
		this.statusDate = statusDate;
	}

	public String getPanelStatus() {
		return panelStatus;
	}

	public void setPanelStatus(String panelStatus) {
		this.panelStatus = panelStatus;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((SMS == null) ? 0 : SMS.hashCode());
		result = prime * result + ((associate == null) ? 0 : associate.hashCode());
		result = prime * result + ((comments == null) ? 0 : comments.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((panelStatus == null) ? 0 : panelStatus.hashCode());
		result = prime * result + ((statusDate == null) ? 0 : statusDate.hashCode());
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
		Panel other = (Panel) obj;
		if (SMS == null) {
			if (other.SMS != null)
				return false;
		} else if (!SMS.equals(other.SMS))
			return false;
		if (associate == null) {
			if (other.associate != null)
				return false;
		} else if (!associate.equals(other.associate))
			return false;
		if (comments == null) {
			if (other.comments != null)
				return false;
		} else if (!comments.equals(other.comments))
			return false;
		if (id != other.id)
			return false;
		if (panelStatus == null) {
			if (other.panelStatus != null)
				return false;
		} else if (!panelStatus.equals(other.panelStatus))
			return false;
		if (statusDate == null) {
			if (other.statusDate != null)
				return false;
		} else if (!statusDate.equals(other.statusDate))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Panel [id=" + id + ", associate=" + associate + ", SMS=" + SMS + ", comments=" + comments
				+ ", statusDate=" + statusDate + ", panelStatus=" + panelStatus + "]";
	}





}