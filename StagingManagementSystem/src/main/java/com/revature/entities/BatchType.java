package com.revature.entities;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.revature.exceptions.SmsCustomException;
import com.revature.markers.SmsValidatable;

/**
 * Created by mnikitin on 5/31/17.
 */
@Entity
@Table(name = "BATCH_TYPES")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class BatchType implements SmsValidatable {
	@Id
	@Column(name = "BATCH_TYPE_ID")
	@SequenceGenerator(name = "BATCH_TYPE_ID_SEQ", sequenceName = "BATCH_TYPE_ID_SEQ")
	@GeneratedValue(generator = "BATCH_TYPE_ID_SEQ", strategy = GenerationType.SEQUENCE)
	private Long id;

	@Column(name = "BATCH_TYPE_VALUE")
	private String value;

	@OneToMany(mappedBy = "batchType")
	private Set<InterviewQuestion> interviewQuestions;

	public BatchType() {
		super();
	}

	public BatchType(Long id, String value, Set<InterviewQuestion> interviewQuestions) {
		super();
		this.id = id;
		this.value = value;
		this.interviewQuestions = interviewQuestions;
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

	public Set<InterviewQuestion> getInterviewQuestions() {
		return interviewQuestions;
	}

	public void setInterviewQuestions(Set<InterviewQuestion> interviewQuestions) {
		this.interviewQuestions = interviewQuestions;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((interviewQuestions == null) ? 0 : interviewQuestions.hashCode());
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
		BatchType other = (BatchType) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (interviewQuestions == null) {
			if (other.interviewQuestions != null)
				return false;
		} else if (!interviewQuestions.equals(other.interviewQuestions))
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
		return "BatchType [id=" + id + ", value=" + value + ", interviewQuestions=" + interviewQuestions + "]";
	}

	@Override
	public void validate() throws SmsCustomException {
		// TODO Validate your members.

	}
}
