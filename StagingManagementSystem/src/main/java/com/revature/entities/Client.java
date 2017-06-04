package com.revature.entities;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.revature.classes.ClientInfo;

@Entity
@Table(name = "Clients")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Client {

	@Id
	@Column(name="CLIENT_ID")
	@SequenceGenerator(name = "CLIENT_ID_SEQ", sequenceName = "CLIENT_ID_SEQ")
	@GeneratedValue(generator = "CLIENT_ID_SEQ", strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "client_name")
	private String name;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,
			mappedBy="client")
	private Set<Job> jobs;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,
			mappedBy="client")
	private Set<Interviews> interviews;

	@OneToMany(cascade = CascadeType.ALL, mappedBy="client")
	private List<ClientQ> clientQuestions;

	public Client() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Client(long id, String name, Set<Job> jobs, Set<Interviews> interviews, List<ClientQ> clientQuestions) {
		super();
		this.id = id;
		this.name = name;
		this.jobs = jobs;
		this.interviews = interviews;
		this.clientQuestions = clientQuestions;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Job> getJobs() {
		return jobs;
	}

	public void setJobs(Set<Job> jobs) {
		this.jobs = jobs;
	}

	public Set<Interviews> getInterviews() {
		return interviews;
	}

	public void setInterviews(Set<Interviews> interviews) {
		this.interviews = interviews;
	}

	public List<ClientQ> getClientQuestions() {
		return clientQuestions;
	}

	public void setClientQuestions(List<ClientQ> clientQuestions) {
		this.clientQuestions = clientQuestions;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((clientQuestions == null) ? 0 : clientQuestions.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
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
		if (getClass() != obj.getClass())
			return false;
		Client other = (Client) obj;
		if (clientQuestions == null) {
			if (other.clientQuestions != null)
				return false;
		} else if (!clientQuestions.equals(other.clientQuestions))
			return false;
		if (id != other.id)
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
		return "Client [id=" + id + ", name=" + name + ", jobs=" + jobs + ", interviews=" + interviews
				+ ", clientQuestions=" + clientQuestions + "]";
	}


	

}
