package com.revature.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Convert;
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
import com.revature.config.SmsSettings;
import com.revature.exceptions.SmsCustomException;
import com.revature.markers.SmsValidatable;
import com.revature.util.LocalDateTimeConverter;

@Entity
@Table(name = "INTERVIEWS")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Interview implements SmsValidatable {

	transient private static SmsSettings settings = SmsSettings.getInstance();

	@Id
	@Column(name = "INTERVIEW_ID")
	@SequenceGenerator(name = "INTERVIEW_ID_SEQ", sequenceName = "INTERVIEW_ID_SEQ")
	@GeneratedValue(generator = "INTERVIEW_ID_SEQ", strategy = GenerationType.SEQUENCE)
	private long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ASSOCIATE_ID")
	private Associate associate;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CLIENT_ID")
	private Client client;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "INTERVIEW_STATUS_ID")
	private InterviewStatuses interviewStatus;

	@Column(name = "INTERVIEW_TIME")
	@Convert(converter = LocalDateTimeConverter.class)
	private LocalDateTime scheduled;

	public Interview() {
		super();
	}

	public Interview(long id, Associate associate, Client client, InterviewStatuses interviewStatus,
			LocalDateTime scheduled) {
		super();
		this.id = id;
		this.associate = associate;
		this.client = client;
		this.interviewStatus = interviewStatus;
		this.scheduled = scheduled;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Associate getAssociate() {
		return associate;
	}

	public void setAssociate(Associate associate) {
		this.associate = associate;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public InterviewStatuses getInterviewStatus() {
		return interviewStatus;
	}

	public void setInterviewStatus(InterviewStatuses interviewStatus) {
		this.interviewStatus = interviewStatus;
	}

	public LocalDateTime getScheduled() {
		return scheduled;
	}

	public void setScheduled(LocalDateTime scheduled) {
		this.scheduled = scheduled;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((associate == null) ? 0 : associate.hashCode());
		result = prime * result + ((client == null) ? 0 : client.hashCode());
		result = prime * result + ((interviewStatus == null) ? 0 : interviewStatus.hashCode());
		result = prime * result + ((scheduled == null) ? 0 : scheduled.hashCode());
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
		Interview other = (Interview) obj;
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
		if (interviewStatus == null) {
			if (other.interviewStatus != null)
				return false;
		} else if (!interviewStatus.equals(other.interviewStatus))
			return false;
		if (scheduled == null) {
			if (other.scheduled != null)
				return false;
		} else if (!scheduled.equals(other.scheduled))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Interview [id=" + id + ", associate=" + associate + ", client=" + client + ", interviewStatus="
				+ interviewStatus + ", scheduled=" + scheduled + "]";
	}

	@Override
	public void validate() throws SmsCustomException {
		// TODO Validate your members.

	}

}
