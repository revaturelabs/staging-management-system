package com.revature.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.revature.exceptions.SmsCustomException;
import com.revature.markers.SmsValidatable;

@Entity
@Table(name = "MANAGERS")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Manager implements SmsValidatable {

	@Id
	@Column(name = "MANAGER_ID")
	@SequenceGenerator(name = "MANAGER_ID_SEQ", sequenceName = "MANAGER_ID_SEQ")
	@GeneratedValue(generator = "MANAGER_ID_SEQ", strategy = GenerationType.SEQUENCE)
	private long id;

	@Column(name = "MANAGER_NAME")
	private String name;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "CREDENTIAL_ID")
	private Credential credential;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PERMISSION_ID")
	private Permission permission;

	@Column(name = "MANAGER_ACTIVE")
	private boolean active;

	public Manager() {
		super();
		this.active = true;
	}

	public Manager(long id, String name, Credential credential, Permission permission, boolean active) {
		super();
		this.id = id;
		this.name = name;
		this.credential = credential;
		this.permission = permission;
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

	public Credential getCredential() {
		return credential;
	}

	public void setCredential(Credential credential) {
		this.credential = credential;
	}

	public Permission getPermission() {
		return permission;
	}

	public void setPermission(Permission permission) {
		this.permission = permission;
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
		result = prime * result + ((credential == null) ? 0 : credential.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((permission == null) ? 0 : permission.hashCode());
		return result;
	}

	@Override
	public final boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Manager))
			return false;
		Manager other = (Manager) obj;
		if (active != other.active)
			return false;
		if (credential == null) {
			if (other.credential != null)
				return false;
		} else if (!credential.equals(other.credential))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (permission == null) {
			if (other.permission != null)
				return false;
		} else if (!permission.equals(other.permission))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Manager [id=" + id + ", name=" + name + ", credential=" + credential + ", permission=" + permission
				+ ", active=" + active + "]";
	}

	@Override
	public void validate() throws SmsCustomException {
		// TODO ='[
	}

}
