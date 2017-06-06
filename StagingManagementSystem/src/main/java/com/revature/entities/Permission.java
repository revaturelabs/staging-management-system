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
import com.revature.exceptions.badrequests.InvalidFieldException;
import com.revature.exceptions.badrequests.NullReferenceException;
import com.revature.markers.SmsValidatable;

@Entity
@Table(name = "PERMISSIONS")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Permission implements SmsValidatable {

	transient private static SmsSettings settings = SmsSettings.getInstance();

	@Id
	@Column(name = "PERMISSION_ID")
	@SequenceGenerator(name = "PERMISSION_ID_SEQ", sequenceName = "PERMISSION_ID_SEQ")
	@GeneratedValue(generator = "PERMISSION_ID_SEQ", strategy = GenerationType.SEQUENCE)
	private Long id;

	@Column(name = "PERMISSION_LEVEL")
	private String level;

	public Permission() {
		super();
	}

	public Permission(Long id, String level) {
		super();
		this.id = id;
		this.level = level;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((level == null) ? 0 : level.hashCode());
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
		Permission other = (Permission) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
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

	@Override
	public void validate() throws SmsCustomException {
		if (this.level == null) {
			throw new NullReferenceException("Permission level is null.");
		}
		if (this.level == "") {
			throw new InvalidFieldException("Permission level is empty.");
		}
		if (!this.level.matches(settings.get("allowed_permission_level"))) {
			throw new InvalidFieldException("Permission level contains illegal characters.");
		}
		int min = Integer.parseInt(settings.get("length_min_permission_level"));
		int max = Integer.parseInt(settings.get("length_max_permission_level"));
		if (this.level.length() < min) {
			throw new InvalidFieldException("Permission level requires " + min + " characters.");
		}
		if (this.level.length() > max) {
			throw new InvalidFieldException("Permission level is limited to " + max + "characters.");
		}
	}

}
