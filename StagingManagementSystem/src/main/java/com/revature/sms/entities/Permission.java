package com.revature.sms.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Table(name = "PERMISSIONS")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Permission {

	@Id
	@Column(name = "PERMISSION_ID")
	@SequenceGenerator(name = "PERMISSION_ID_SEQ", sequenceName = "PERMISSION_ID_SEQ")
	@GeneratedValue(generator = "PERMISSION_ID_SEQ", strategy = GenerationType.SEQUENCE)
	private long id;

	@Column(name = "PERMISSION_LEVEL")
	private String level;

	public Permission() {
		super();
	}

	public Permission(long id, String level) {
		super();
		this.id = id;
		this.level = level;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((level == null) ? 0 : level.hashCode());
		return result;
	}

	@Override
	final public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Permission))
			return false;
		Permission other = (Permission) obj;
		if (level == null) {
			if (other.level != null)
				return false;
		} else if (!level.equals(other.level))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Permission [id=" + id + ", level=" + level + "]";
	}


}
