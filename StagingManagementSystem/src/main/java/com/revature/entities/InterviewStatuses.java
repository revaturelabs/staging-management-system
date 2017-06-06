package com.revature.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.revature.exceptions.SmsCustomException;
import com.revature.markers.SmsValidatable;

@Entity
@Table(name = "INTERVIEW_STATUSES")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class InterviewStatuses implements SmsValidatable {

	@Id
	@Column(name = "INTERVIEW_STATUS_ID")
	@SequenceGenerator(name = "INTERVIEW_STATUS_ID_SEQ", sequenceName = "INTERVIEW_STATUS_ID_SEQ")
	@GeneratedValue(generator = "INTERVIEW_STATUS_ID_SEQ", strategy = GenerationType.SEQUENCE)
	private Long id;

	@Column(name = "INTERVIEW_STATUS_VALUE")
	private String value;

	public InterviewStatuses() {
		super();
	}

	public InterviewStatuses(Long id, String value) {
		super();
		this.id = id;
		this.value = value;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
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
		InterviewStatuses other = (InterviewStatuses) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "InterviewStatuses [id=" + id + ", value=" + value + "]";
	}

	@Override
	public void validate() throws SmsCustomException {
		// TODO Validate your members.

	}

}
