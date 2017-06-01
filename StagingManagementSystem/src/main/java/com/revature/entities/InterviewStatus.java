package com.revature.entities;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="interview_statuses")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class InterviewStatus {
	@Id
	@Column(name="interview_Status_Id")
	@SequenceGenerator(name="interview_statuses_seq", sequenceName="interview_statuses_seq")
	@GeneratedValue(generator="interview_statuses_seq", strategy=GenerationType.AUTO)
	private long id;
	private String status;
	@OneToMany(fetch=FetchType.EAGER, mappedBy="status_Id")
	private Collection<Interviews> interviews;
	public InterviewStatus() {
		super();
	}
	public InterviewStatus(long id, String status, Collection<Interviews> interviews) {
		super();
		this.id = id;
		this.status = status;
		this.interviews = interviews;
	}
	@Override
	public String toString() {
		return "InterviewStatus [id=" + id + ", status=" + status + ", interviews=" + interviews + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((interviews == null) ? 0 : interviews.hashCode());
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
		InterviewStatus other = (InterviewStatus) obj;
		if (id != other.id)
			return false;
		if (interviews == null) {
			if (other.interviews != null)
				return false;
		} else if (!interviews.equals(other.interviews))
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
	public Collection<Interviews> getInterviews() {
		return interviews;
	}
	public void setInterviews(Collection<Interviews> interviews) {
		this.interviews = interviews;
	}
	
	
	
	
}
