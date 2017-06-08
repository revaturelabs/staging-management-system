package com.revature.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.revature.config.SmsSettings;
import com.revature.exceptions.SmsCustomException;
import com.revature.markers.SmsValidatable;

@Entity
@Table(name = "Clients")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Client implements SmsValidatable {

	transient private static SmsSettings settings = SmsSettings.getInstance();

	@Id
	@Column(name = "CLIENT_ID")
	@SequenceGenerator(name = "CLIENT_ID_SEQ", sequenceName = "CLIENT_ID_SEQ")
	@GeneratedValue(generator = "CLIENT_ID_SEQ", strategy = GenerationType.SEQUENCE)
	private long id;

	@Column(name = "CLIENT_NAME")
	private String name;

	@Column(name = "CLIENT_PRIORITY")
	private boolean priority;

	@Column(name = "CLIENT_ACTIVE")
	private boolean active;

	public Client() {
		super();
		this.active = true;
	}

	public Client(long id, String name, boolean priority, boolean active) {
		super();
		this.id = id;
		this.name = name;
		this.priority = priority;
		this.active = active;
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

	public boolean isPriority() {
		return priority;
	}

	public void setPriority(boolean priority) {
		this.priority = priority;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (active ? 1231 : 1237);
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + (priority ? 1231 : 1237);
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
		if (active != other.active)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (priority != other.priority)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Client [id=" + id + ", name=" + name + ", priority=" + priority + ", active=" + active + "]";
	}

	@Override
	public void validate() throws SmsCustomException {
		// TODO Validate your members.

	}

}
