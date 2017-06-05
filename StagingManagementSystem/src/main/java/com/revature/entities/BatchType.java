package com.revature.entities;

import java.util.HashSet;
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
	private long id;

	@Column(name = "BATCH_TYPE")
	private String type;

	@OneToMany(mappedBy = "batchType")
	private Set<InterviewQuestion> interviewQuestions;

	public BatchType() {
		super();
		this.interviewQuestions = new HashSet<InterviewQuestion>();
	}

	public BatchType(long id, String type, Set<InterviewQuestion> interviewQuestions) {
		super();
		this.id = id;
		this.type = type;
		this.interviewQuestions = interviewQuestions;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		BatchType batchType = (BatchType) o;

		if (id != batchType.id)
			return false;
		return type.equals(batchType.type);
	}

	@Override
	public int hashCode() {
		int result = (int) (id ^ (id >>> 32));
		result = 31 * result + type.hashCode();
		return result;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public void validate() throws SmsCustomException {
		// TODO Validate your members.

	}
}
