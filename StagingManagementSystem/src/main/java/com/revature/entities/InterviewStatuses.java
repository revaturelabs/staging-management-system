package com.revature.entities;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.revature.exceptions.SmsCustomException;
import com.revature.markers.SmsValidatable;

@Entity
@Table(name = "interview_statuses")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class InterviewStatuses implements SmsValidatable {

	@Id
	@Column(name = "INTERVIEW_STATUS_ID")
	@SequenceGenerator(name = "INTERVIEW_STATUS_ID_SEQ", sequenceName = "INTERVIEW_STATUS_ID_SEQ")
	@GeneratedValue(generator = "INTERVIEW_STATUS_ID_SEQ", strategy = GenerationType.SEQUENCE)
	private long id;

	@Column(name = "INTERVIEW_STATUS")
	private String status;

	public InterviewStatuses() {
		super();
	}

	public InterviewStatuses(long id, String status, Collection<Interviews> interviews) {
		super();
		this.id = id;
		this.status = status;
	}

	@Override
	public String toString() {
		return "InterviewStatus [id=" + id + ", status=" + status + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((status == null) ? 0 : status.hashCode());
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
		InterviewStatuses other = (InterviewStatuses) obj;
		if (id != other.id)
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		return true;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public void validate() throws SmsCustomException {
		// TODO Validate your members.

	}

}
