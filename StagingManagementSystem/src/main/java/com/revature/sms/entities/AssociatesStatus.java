package com.revature.sms.entities;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="ASSOCIATES_STATUS")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class AssociatesStatus
{
	@Id
	@Column(name= "ASSOCIATE_STATUS_ID")
//	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ASSOCIATE_STATUS_ID_SEQ")
//	@SequenceGenerator(name = "ASSOCIATE_STATUS_ID_SEQ", sequenceName = "ASSOCIATE_STATUS_ID_SEQ")
	private int associateStatusId;
	
	@Column(name="STATUS")
	private String status;

	public AssociatesStatus() 
	{
		super();
	}

	public AssociatesStatus(int associateStatusId, String status) 
	{
		super();
		this.associateStatusId = associateStatusId;
		this.status = status;
	}

	public int getAssociatesStatusId() 
	{
		return associateStatusId;
	}

	public void setAssociatesStatusId(int associateStatusId) 
	{
		this.associateStatusId = associateStatusId;
	}

	public String getStatus() 
	{
		return status;
	}

	public void setStatus(String status) 
	{
		this.status = status;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + associateStatusId;
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) 
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AssociatesStatus other = (AssociatesStatus) obj;
		if (associateStatusId != other.associateStatusId)
			return false;
		if (status == null) 
		{
			if (other.status != null)
				return false;
		}
		else if (!status.equals(other.status))
			return false;
		return true;
	}

	@Override
	public String toString() 
	{
		return "AssociatesStatus [associateStatusId=" + associateStatusId + ", status=" + status + "]";
	} 
}
