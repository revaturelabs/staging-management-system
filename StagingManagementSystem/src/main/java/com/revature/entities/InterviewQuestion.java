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
	@GeneratedValue(generator = "INTERVIEW_QUESTION_ID_SEQ", strategy = GenerationType.AUTO)
	private long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "BATCH_TYPE_ID")
	private BatchType batchType;

	@Column
	private String question;

	public InterviewQuestion() {
		super();
	}

	public InterviewQuestion(long id, BatchType batchType, String question) {
		super();
		this.id = id;
		this.batchType = batchType;
		this.question = question;
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

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((batchType == null) ? 0 : batchType.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((question == null) ? 0 : question.hashCode());
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
		InterviewQuestion other = (InterviewQuestion) obj;
		if (batchType == null) {
			if (other.batchType != null)
				return false;
		} else if (!batchType.equals(other.batchType))
			return false;
		if (id != other.id)
			return false;
		if (question == null) {
			if (other.question != null)
				return false;
		} else if (!question.equals(other.question))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "InterviewQuestion [id=" + id + ", batchType=" + batchType + ", question=" + question + "]";
	}

	@Override
	public void validate() throws SmsCustomException {
		// TODO Validate your members.

	}

}
