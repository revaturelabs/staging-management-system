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

/**
 * Created by mnikitin on 5/31/17.
 */
@Entity
@Table(name = "TRAINERS")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Trainer implements SmsValidatable {

	transient private static SmsSettings settings = SmsSettings.getInstance();

	@Id
	@Column(name = "TRAINER_ID")
	@SequenceGenerator(name = "TRAINER_ID_SEQ", sequenceName = "TRAINER_ID_SEQ")
	@GeneratedValue(generator = "TRAINER_ID_SEQ", strategy = GenerationType.SEQUENCE)
	private Long id;

	@Column(name = "TRAINER_NAME")
	private String name;

	@Column(name = "TRAINER_ACTIVE")
	private Boolean active;

	public Trainer() {
		super();
	}

	public Trainer(Long id, String name, Boolean active) {
		super();
		this.id = id;
		this.name = name;
		this.active = active;
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

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((active == null) ? 0 : active.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Trainer other = (Trainer) obj;
		if (active == null) {
			if (other.active != null)
				return false;
		} else if (!active.equals(other.active))
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
		return true;
	}

	@Override
	public String toString() {
		return "Trainer [id=" + id + ", name=" + name + ", active=" + active + "]";
	}

	@Override
	public void validate() throws SmsCustomException {
		// TODO Validate your members.

	}
}
