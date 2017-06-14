package com.revature.entities;

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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.revature.exceptions.SmsCustomException;
import com.revature.markers.SmsValidatable;
import com.revature.util.LocalDateTimeConverter;

@Entity
@Table(name = "JOBS")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Job implements SmsValidatable {

	@Id
	@Column(name = "JOB_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "JOB_ID_SEQ")
	@SequenceGenerator(name = "JOB_ID_SEQ", sequenceName = "JOB_ID_SEQ")
	private long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ASSOCIATE_ID")
	@JsonProperty(access=Access.WRITE_ONLY)
	private Associate associate;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CLIENT_ID")
	private Client client;

	@Column(name = "JOB_START_DATE")
	@Convert(converter = LocalDateTimeConverter.class)
	private LocalDateTime startDate;

	@Column(name = "JOB_PROJECTED_END_DATE")
	@Convert(converter = LocalDateTimeConverter.class)
	private LocalDateTime projectedEndDate;

	@Column(name = "JOB_ACTUAL_END_DATE")
	@Convert(converter = LocalDateTimeConverter.class)
	private LocalDateTime endDate;

	@Column(name = "JOB_BUYOUT_DATE")
	@Convert(converter = LocalDateTimeConverter.class)
	private LocalDateTime buyoutDate;

	@Column(name = "JOB_CONFIRMED_DATE")
	@Convert(converter = LocalDateTimeConverter.class)
	private LocalDateTime confirmedDate;

	public Job() {
		super();
	}

	public Job(long id, Associate associate, Client client, LocalDateTime startDate, LocalDateTime projectedEndDate,
			LocalDateTime endDate, LocalDateTime buyoutDate, LocalDateTime confirmedDate) {
		super();
		this.id = id;
		this.associate = associate;
		this.client = client;
		this.startDate = startDate;
		this.projectedEndDate = projectedEndDate;
		this.endDate = endDate;
		this.buyoutDate = buyoutDate;
		this.confirmedDate = confirmedDate;
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

	public LocalDateTime getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDateTime startDate) {
		this.startDate = startDate;
	}

	public LocalDateTime getProjectedEndDate() {
		return projectedEndDate;
	}

	public void setProjectedEndDate(LocalDateTime projectedEndDate) {
		this.projectedEndDate = projectedEndDate;
	}

	public LocalDateTime getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDateTime endDate) {
		this.endDate = endDate;
	}

	public LocalDateTime getBuyoutDate() {
		return buyoutDate;
	}

	public void setBuyoutDate(LocalDateTime buyoutDate) {
		this.buyoutDate = buyoutDate;
	}

	public LocalDateTime getConfirmedDate() {
		return confirmedDate;
	}

	public void setConfirmedDate(LocalDateTime confirmedDate) {
		this.confirmedDate = confirmedDate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((associate == null) ? 0 : associate.hashCode());
		result = prime * result + ((buyoutDate == null) ? 0 : buyoutDate.hashCode());
		result = prime * result + ((client == null) ? 0 : client.hashCode());
		result = prime * result + ((confirmedDate == null) ? 0 : confirmedDate.hashCode());
		result = prime * result + ((endDate == null) ? 0 : endDate.hashCode());
		result = prime * result + ((projectedEndDate == null) ? 0 : projectedEndDate.hashCode());
		result = prime * result + ((startDate == null) ? 0 : startDate.hashCode());
		return result;
	}

	@Override
	public final boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Job))
			return false;
		Job other = (Job) obj;
		if (associate == null) {
			if (other.associate != null)
				return false;
		} else if (!associate.equals(other.associate))
			return false;
		if (buyoutDate == null) {
			if (other.buyoutDate != null)
				return false;
		} else if (!buyoutDate.equals(other.buyoutDate))
			return false;
		if (client == null) {
			if (other.client != null)
				return false;
		} else if (!client.equals(other.client))
			return false;
		if (confirmedDate == null) {
			if (other.confirmedDate != null)
				return false;
		} else if (!confirmedDate.equals(other.confirmedDate))
			return false;
		if (endDate == null) {
			if (other.endDate != null)
				return false;
		} else if (!endDate.equals(other.endDate))
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
		return "Job [id=" + id + ", associate=" + associate + ", client=" + client + ", startDate=" + startDate
				+ ", projectedEndDate=" + projectedEndDate + ", endDate=" + endDate + ", buyoutDate=" + buyoutDate
				+ ", confirmedDate=" + confirmedDate + "]";
	}

	@Override
	public void validate() throws SmsCustomException {
		// TODO Validate your members.

	}
}
