package com.revature.entities;

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
@Table(name="interviews")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Interviews {
	@Id
	@Column
	@SequenceGenerator(name="interviews_seq", sequenceName="interviews_seq")
	@GeneratedValue(generator="interviews_seq", strategy=GenerationType.AUTO)
	private long interview_id;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="associate_Id")
	private Associate associate;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="client_Id")
	private Client client;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="status_Id")
	private InterviewStatus status;
	
	public Interviews() {
		super();
	}
	public Interviews(long interview_id, Associate associate, Client client, InterviewStatus status) {
		super();
		this.interview_id = interview_id;
		this.associate = associate;
		this.client = client;
		this.status = status;
	}
	@Override
	public String toString() {
		return "Interviews [interview_id=" + interview_id + ", associate=" + associate + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((associate == null) ? 0 : associate.hashCode());
		result = prime * result + (int) (interview_id ^ (interview_id >>> 32));
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
		Interviews other = (Interviews) obj;
		if (associate == null) {
			if (other.associate != null)
				return false;
		} else if (!associate.equals(other.associate))
			return false;
		if (interview_id != other.interview_id)
			return false;
		return true;
	}
	public long getInterview_id() {
		return interview_id;
	}
	public void setInterview_id(long interview_id) {
		this.interview_id = interview_id;
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
	public InterviewStatus getStatus() {
		return status;
	}
	public void setStatus(InterviewStatus status) {
		this.status = status;
	}
	
	
		
}
