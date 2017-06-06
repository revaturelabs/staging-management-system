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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.revature.exceptions.SmsCustomException;
import com.revature.markers.SmsValidatable;

@Entity
@Table(name = "CLIENT_QUESTIONS")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class ClientQuestion implements SmsValidatable {
	@Id
	@Column(name = "CLIENT_QUESTION_ID")
	@SequenceGenerator(name = "CLIENT_QUESTION_ID_SEQ", sequenceName = "CLIENT_QUESTION_ID_SEQ")
	@GeneratedValue(generator = "CLIENT_QUESTION_ID_SEQ", strategy = GenerationType.SEQUENCE)
	private Long ClientQuestionId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CLIENT_ID")
	private Client client;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "INTERVIEW_QUESTION_ID")
	private InterviewQuestion interviewQ;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ASSOCIATE_ID")
	private Associate associate;

	public ClientQuestion() {
		super();
	}

	public ClientQuestion(Long clientQuestionId, Client client, InterviewQuestion interviewQ, Associate associate) {
		super();
		ClientQuestionId = clientQuestionId;
		this.client = client;
		this.interviewQ = interviewQ;
		this.associate = associate;
	}

	public Long getClientQuestionId() {
		return ClientQuestionId;
	}

	public void setClientQuestionId(Long clientQuestionId) {
		ClientQuestionId = clientQuestionId;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public InterviewQuestion getInterviewQ() {
		return interviewQ;
	}

	public void setInterviewQ(InterviewQuestion interviewQ) {
		this.interviewQ = interviewQ;
	}

	public Associate getAssociate() {
		return associate;
	}

	public void setAssociate(Associate associate) {
		this.associate = associate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ClientQuestionId == null) ? 0 : ClientQuestionId.hashCode());
		result = prime * result + ((associate == null) ? 0 : associate.hashCode());
		result = prime * result + ((client == null) ? 0 : client.hashCode());
		result = prime * result + ((interviewQ == null) ? 0 : interviewQ.hashCode());
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
		ClientQuestion other = (ClientQuestion) obj;
		if (ClientQuestionId == null) {
			if (other.ClientQuestionId != null)
				return false;
		} else if (!ClientQuestionId.equals(other.ClientQuestionId))
			return false;
		if (associate == null) {
			if (other.associate != null)
				return false;
		} else if (!associate.equals(other.associate))
			return false;
		if (client == null) {
			if (other.client != null)
				return false;
		} else if (!client.equals(other.client))
			return false;
		if (interviewQ == null) {
			if (other.interviewQ != null)
				return false;
		} else if (!interviewQ.equals(other.interviewQ))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ClientQuestion [ClientQuestionId=" + ClientQuestionId + ", client=" + client + ", interviewQ="
				+ interviewQ + ", associate=" + associate + "]";
	}

	@Override
	public void validate() throws SmsCustomException {
		// TODO Validate your members.

	}
}
