package com.revature.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.revature.exceptions.SmsCustomException;
import com.revature.markers.SmsValidatable;

@Entity
@Table(name = "INTERVIEW_QUESTIONS")
public class InterviewQuestion implements SmsValidatable {

	@Id
	@Column(name = "INTERVIEW_QUESTION_ID")
	@SequenceGenerator(name = "INTERVIEW_QUESTION_ID_SEQ", sequenceName = "INTERVIEW_QUESTION_ID_SEQ")
	@GeneratedValue(generator = "INTERVIEW_QUESTION_ID_SEQ", strategy = GenerationType.SEQUENCE)
	private long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "BATCH_TYPE_ID")
	private BatchType batchType;

	@Column(name = "INTERVIEW_QUESTION_VALUE")
	private String value;

	public InterviewQuestion() {
		super();
	}

	public InterviewQuestion(long id, BatchType batchType, String value) {
		super();
		this.id = id;
		this.batchType = batchType;
		this.value = value;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public BatchType getBatchType() {
		return batchType;
	}

	public void setBatchType(BatchType batchType) {
		this.batchType = batchType;
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
		result = prime * result + ((batchType == null) ? 0 : batchType.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}

	@Override
	public final boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof InterviewQuestion))
			return false;
		InterviewQuestion other = (InterviewQuestion) obj;
		if (batchType == null) {
			if (other.batchType != null)
				return false;
		} else if (!batchType.equals(other.batchType))
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
		return "InterviewQuestion [id=" + id + ", batchType=" + batchType + ", value=" + value + "]";
	}

	@Override
	public void validate() throws SmsCustomException {
		// TODO Validate your members.

	}

}
