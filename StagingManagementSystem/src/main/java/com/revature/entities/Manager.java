package com.revature.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "MANAGERS")
public class Manager {

	@Id
	@Column(name = "MANAGER_ID")
	@SequenceGenerator(name="MANAGER_ID_SEQ", sequenceName="MANAGER_ID_SEQ")
	@GeneratedValue(generator="MANAGER_ID_SEQ", strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(name = "MANAGER_NAME")
	private String name;
	
	@OneToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name="CREDENTIAL_ID")
	private Credential credential;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="PERMISSION_ID")
	private Permission permission;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="approvedBy")
	private Set<Checkin> approved;

	public Manager(Long id, String name, Credential credential, Permission permission, Set<Checkin> approved) {
		super();
		this.id = id;
		this.name = name;
		this.credential = credential;
		this.permission = permission;
		this.approved = approved;
	}

	public Manager() {
		super();
		this.approved = new HashSet<Checkin>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public Set<Checkin> getApproved() {
		return approved;
	}

	public void setApproved(Set<Checkin> approved) {
		this.approved = approved;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((credential == null) ? 0 : credential.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((permission == null) ? 0 : permission.hashCode());
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
		Manager other = (Manager) obj;
		if (credential == null) {
			if (other.credential != null)
				return false;
		} else if (!credential.equals(other.credential))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
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
				+ "]";
	} 

	
}
