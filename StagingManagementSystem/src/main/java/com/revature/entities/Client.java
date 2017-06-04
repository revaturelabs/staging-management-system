package com.revature.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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

	public Client() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Client(long clientId, String name, ClientInfo clientInfo) {
		super();
		this.id = clientId;
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public void setId(long clientId) {
		this.id = clientId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
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
		
		if (id != other.id)
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
		return "Client [id=" + id + ", name=" + name + "]";
	}

}
