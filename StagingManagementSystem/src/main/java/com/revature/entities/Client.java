package com.revature.entities;

import java.util.List;
import java.util.Set;

import javax.persistence.*;

import com.revature.bean.GeneratedValue;
import com.revature.bean.SequenceGenerator;



@Entity
@Table(name="Client")
public class Client {
	
	@SequenceGenerator(name="CLIENT_ID_SEQ", sequenceName="CLIENT_ID_SEQ")
	@GeneratedValue(generator="CLIENT_ID_SEQ", strategy=GenerationType.AUTO)
	private int client_id;
	
	@Column(name="client_name")
	private String name;
	
	@OneToOne(cascade=CascadeType.ALL)
	private ClientInfo clientInfo;
	
	@OneToMany(cascade=CascadeType.ALL, fetch = fetchType.LAZY)
	private List<Jobs> jobs;

	@OneToMany(cascade=CascadeType.ALL, fetch = fetchType.LAZY)
	private List<Interviews> interviews;
	
	@OneToMany(cascade=CascadeType.ALL)
	private List<ClientQuestions> clientQuestions;
	
	@ManyToMany(cascade=CascadeType.ALL, fetch = fetchType.LAZY)
	private Set<Associate> associates;

	public Client(long id, String name, ClientInfo clientInfo, List<Jobs> jobs, List<Interviews> interviews,
			List<ClientQuestions> clientQuestions, Set<Associate> associates) {
		super();
		this.id = id;
		this.name = name;
		this.clientInfo = clientInfo;
		this.jobs = jobs;
		this.interviews = interviews;
		this.clientQuestions = clientQuestions;
		this.associates = associates;
	}

	public Client() {
		super();
		// TODO Auto-generated constructor stub
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

	public ClientInfo getClientInfo() {
		return clientInfo;
	}

	public void setClientInfo(ClientInfo clientInfo) {
		this.clientInfo = clientInfo;
	}

	public List<Jobs> getJobs() {
		return jobs;
	}

	public void setJobs(List<Jobs> jobs) {
		this.jobs = jobs;
	}

	public List<Interviews> getInterviews() {
		return interviews;
	}

	public void setInterviews(List<Interviews> interviews) {
		this.interviews = interviews;
	}

	public List<ClientQuestions> getClientQuestions() {
		return clientQuestions;
	}

	public void setClientQuestions(List<ClientQuestions> clientQuestions) {
		this.clientQuestions = clientQuestions;
	}

	public Set<Associate> getAssociates() {
		return associates;
	}

	public void setAssociates(Set<Associate> associates) {
		this.associates = associates;
	}

	@Override
	public String toString() {
		return "Client [id=" + id + ", name=" + name + ", associates=" + associates + "]";
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
