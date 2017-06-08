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
@Table(name = "CREDENTIALS")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Credential implements SmsValidatable {

	transient private static SmsSettings settings = SmsSettings.getInstance();
	
	@Id
	@Column(name = "CREDENTIAL_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CREDENTIAL_ID_SEQ")
	@SequenceGenerator(name = "CREDENTIAL_ID_SEQ", sequenceName = "CREDENTIAL_ID_SEQ")
	private Long id;

	@Column(name = "CREDENTIAL_USERNAME")
	private String username;

	@Column(name = "CREDENTIAL_PASSWORD")
	private String password;

	public Credential(Long id, String username, String password) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
	}

	public Credential() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Credential))
			return false;
		Credential other = (Credential) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Credential [id=" + id + ", username=" + username + ", password=" + password + "]";
	}

	@Override
	public void validate() throws SmsCustomException {
		// TODO Validate your members.

	}

}
