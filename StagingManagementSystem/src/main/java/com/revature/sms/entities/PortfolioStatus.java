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
@Table(name="PORTFOLIO_STATUS")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class PortfolioStatus 
{
	@Id
	@Column(name = "PORTFOLIO_STATUS_ID")
//	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PORTFOLIO_STATUS_ID_SEQ")
//	@SequenceGenerator(name = "PORTFOLIO_STATUS_ID_SEQ", sequenceName = "PORTFOLIO_STATUS_ID_SEQ")
	private int portfolioStatusId;
	
	@Column(name="PORTFOLIO_STATUS")
	private String portfolioStatus;

	public PortfolioStatus() 
	{
		super();
	}

	public PortfolioStatus(int portfolioStatusId, String portfolioStatus) 
	{
		super();
		this.portfolioStatusId = portfolioStatusId;
		this.portfolioStatus = portfolioStatus;
	}

	public int getPortfolioStatusId()
	{
		return portfolioStatusId;
	}

	public void setPortfolioStatusId(int portfolioStatusId) 
	{
		this.portfolioStatusId = portfolioStatusId;
	}

	public String getPortfolioStatus() 
	{
		return portfolioStatus;
	}

	public void setPortfolioStatus(String portfolioStatus) 
	{
		this.portfolioStatus = portfolioStatus;
	}

	@Override
	public int hashCode() 
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((portfolioStatus == null) ? 0 : portfolioStatus.hashCode());
		result = prime * result + portfolioStatusId;
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
		PortfolioStatus other = (PortfolioStatus) obj;
		if (portfolioStatus == null) 
		{
			if (other.portfolioStatus != null)
				return false;
		}
		else if (!portfolioStatus.equals(other.portfolioStatus))
			return false;
		if (portfolioStatusId != other.portfolioStatusId)
			return false;
		return true;
	}

	@Override
	public String toString() 
	{
		return "PortfolioStatus [portfolioStatusId=" + portfolioStatusId + ", portfolioStatus=" + portfolioStatus + "]";
	}
}
