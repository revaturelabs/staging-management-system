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
@Table(name = "TRAINERS")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Trainer {

	@Id
	@Column(name = "TRAINER_ID")
	@SequenceGenerator(name = "TRAINER_ID_SEQ", sequenceName = "TRAINER_ID_SEQ")
	@GeneratedValue(generator = "TRAINER_ID_SEQ", strategy = GenerationType.SEQUENCE)
	private long id;

	@Column(name = "TRAINER_NAME")
	private String name;

	@Column(name = "TRAINER_ACTIVE")
	private boolean active;

	public Trainer() {
		super();
		this.active=true;
	}

	public Trainer(long id, String name, boolean active) {
		super();
		this.id = id;
		this.name = name;
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
		return result;
	}

	@Override
	final public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Trainer))
			return false;
		Trainer other = (Trainer) obj;
		if (active != other.active)
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

}
