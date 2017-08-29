package com.revature.sms.entities;


import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.CascadeType;

import com.revature.sms.util.LocalDateTimeConverter;

@Entity
@Table(name = "PANEL")
public class Panel 
{
	@Id
	@Column(name = "PANEL_ID")
	@SequenceGenerator(name = "PANEL_ID_SEQ", sequenceName = "PANEL_ID_SEQ",allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PANEL_ID_SEQ")
	
	private long id;
	
	@ManyToOne(cascade=CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "ASSOCIATE_ID")
	private Associate associate;
	
	private String comments;
	
	@Column(name="STATUS_DATE ")
	@Convert(converter = LocalDateTimeConverter.class)
	private LocalDateTime statusDate;
	
	@Column(name="STATUS")
	private String status;
	
	
	public Panel()
	{
		super();
		this.status="PENDING";
	}


	public Panel(long id, Associate associate, String comments, LocalDateTime statusDate, String status) {
		super();
		this.id = id;
		this.associate = associate;
		this.comments = comments;
		this.statusDate = statusDate;
		this.status = status;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((associate == null) ? 0 : associate.hashCode());
		result = prime * result + ((comments == null) ? 0 : comments.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((status == null) ? 0 : status.hashCode());
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
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
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
		return "Panel [id=" + id + ", associate=" + associate + ", comments=" + comments + ", statusDate=" + statusDate
				+ ", status=" + status + "]";
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


	public String getComments() {
		return comments;
	}


	public void setComments(String comments) {
		this.comments = comments;
	}


	public LocalDateTime getStatusDate() {
		return statusDate;
	}


	public void setStatusDate(LocalDateTime statusDate) {
		this.statusDate = statusDate;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}
	
}