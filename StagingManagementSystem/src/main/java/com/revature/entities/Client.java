package com.revature.entities;

import java.util.List;
import java.util.Set;
import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.revature.classes.ClientInfo;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.*;
import com.revature.classes.ClientInfo;

@Entity
@Table(name = "Clients")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Client {

	@Id
	@Column(name="CLIENT_ID")
	@SequenceGenerator(name = "CLIENT_ID_SEQ", sequenceName = "CLIENT_ID_SEQ")
	@GeneratedValue(generator = "CLIENT_ID_SEQ", strategy = GenerationType.AUTO)
	private long clientId;

	@Column(name = "client_name")
	private String name;

	@OneToOne(cascade = CascadeType.ALL)
	private ClientInfo clientInfo;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Job> jobs;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Interviews> interviews;

	@OneToMany(cascade = CascadeType.ALL)
	private List<ClientQ> clientQuestions;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<Associate> associates;

	public Client() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Client(long clientId, String name, ClientInfo clientInfo, List<Job> jobs, List<Interviews> interviews,
			List<ClientQ> clientQuestions, Set<Associate> associates) {
		super();
		this.clientId = clientId;
		this.name = name;
		this.clientInfo = clientInfo;
		this.jobs = jobs;
		this.interviews = interviews;
		this.clientQuestions = clientQuestions;
		this.associates = associates;
	}

	public long getClientId() {
		return clientId;
	}

	public void setClientId(long clientId) {
		this.clientId = clientId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ClientInfo getClientInfo() {
		return clientInfo;
	}

	public void setClientInfo(ClientInfo clientInfo) {
		this.clientInfo = clientInfo;
	}

	public List<Job> getJobs() {
		return jobs;
	}

	public void setJobs(List<Job> jobs) {
		this.jobs = jobs;
	}

	public List<Interviews> getInterviews() {
		return interviews;
	}

	public void setInterviews(List<Interviews> interviews) {
		this.interviews = interviews;
	}

	public List<ClientQ> getClientQuestions() {
		return clientQuestions;
	}

	public void setClientQuestions(List<ClientQ> clientQuestions) {
		this.clientQuestions = clientQuestions;
	}

	public Set<Associate> getAssociates() {
		return associates;
	}

	public void setAssociates(Set<Associate> associates) {
		this.associates = associates;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((associates == null) ? 0 : associates.hashCode());
		result = prime * result + (int) (clientId ^ (clientId >>> 32));
		result = prime * result + ((clientInfo == null) ? 0 : clientInfo.hashCode());
		result = prime * result + ((clientQuestions == null) ? 0 : clientQuestions.hashCode());
		result = prime * result + ((interviews == null) ? 0 : interviews.hashCode());
		result = prime * result + ((jobs == null) ? 0 : jobs.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Client))
			return false;
		Client other = (Client) obj;
		if (associates == null) {
			if (other.associates != null)
				return false;
		} else if (!associates.equals(other.associates))
			return false;
		if (clientId != other.clientId)
			return false;
		if (clientInfo == null) {
			if (other.clientInfo != null)
				return false;
		} else if (!clientInfo.equals(other.clientInfo))
			return false;
		if (clientQuestions == null) {
			if (other.clientQuestions != null)
				return false;
		} else if (!clientQuestions.equals(other.clientQuestions))
			return false;
		if (interviews == null) {
			if (other.interviews != null)
				return false;
		} else if (!interviews.equals(other.interviews))
			return false;
		if (jobs == null) {
			if (other.jobs != null)
				return false;
		} else if (!jobs.equals(other.jobs))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Client [clientId=" + clientId + ", name=" + name + ", clientInfo=" + clientInfo + ", jobs=" + jobs
				+ ", interviews=" + interviews + ", clientQuestions=" + clientQuestions + ", associates=" + associates
				+ "]";
	}

}
