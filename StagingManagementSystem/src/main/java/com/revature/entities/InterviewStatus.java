package com.revature.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="interview_statuses")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class InterviewStatus {
	@Id
	@Column
	@SequenceGenerator(name="interview_statuses_seq", sequenceName="interview_statuses_seq")
	@GeneratedValue(generator="interview_statuses_seq", strategy=GenerationType.AUTO)
	private long status_Id;
	private String status;
	public InterviewStatus() {
		super();
		// TODO Auto-generated constructor stub
	}
	public InterviewStatus(long status_Id, String status) {
		super();
		this.status_Id = status_Id;
		this.status = status;
	}
	@Override
	public String toString() {
		return "InterviewStatus [status_Id=" + status_Id + ", status=" + status + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + (int) (status_Id ^ (status_Id >>> 32));
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
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (status_Id != other.status_Id)
			return false;
		return true;
	}
	public long getStatus_Id() {
		return status_Id;
	}
	public void setStatus_Id(long status_Id) {
		this.status_Id = status_Id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
}
