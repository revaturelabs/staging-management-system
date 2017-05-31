package com.revature.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "CREDENTIALS")
public class Credential {

	@Id
	@Column(name = "CREDENTIAL_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CREDENTIAL_ID_SEQ")
	@SequenceGenerator(name = "CREDENTIAL_ID_SEQ", sequenceName = "CREDENTIAL_ID_SEQ")
	private Long id;

	@Column
	private String username;

	@Column
	private String password;

	public Credential(Long id, String username, String password) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
	}

	public Credential() {
		super();
		// TODO Auto-generated constructor stub
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
	public String toString() {
		return "Credential [id=" + id + ", username=" + username + ", password=" + password + "]";
	}

}
