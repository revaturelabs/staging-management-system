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
@Table(name = "CHECKINS")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Checkin implements SmsValidatable {
	
	transient private static SmsSettings settings = SmsSettings.getInstance();
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CHECKIN_ID_SEQ")
	@SequenceGenerator(name = "CHECKIN_ID_SEQ", sequenceName = "CHECKIN_ID_SEQ")
	@Column(name = "CHECKIN_ID")
	private Long id;

	@Column(name = "CHECKIN_IN_TIME")
	@Convert(converter = LocalDateTimeConverter.class)
	private LocalDateTime checkinTime;

	@Column(name = "CHECKIN_OUT_TIME")
	@Convert(converter = LocalDateTimeConverter.class)
	private LocalDateTime checkoutTime;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "MANAGER_ID")
	private Manager approvedBy;

	@Column(name = "CHECKIN_APPROVE_TIME")
	@Convert(converter = LocalDateTimeConverter.class)
	private LocalDateTime approveTime;

	@ManyToOne
	@JoinColumn(name = "ASSOCIATE_ID")
	private Associate associate;

	public Checkin() {
		super();
	}

	public Long getId() {
		return id;
	}

	public Checkin(Long id, LocalDateTime checkinTime, LocalDateTime checkoutTime, Manager approvedBy,
			LocalDateTime approveTime, Associate associate) {
		super();
		this.id = id;
		this.checkinTime = checkinTime;
		this.checkoutTime = checkoutTime;
		this.approvedBy = approvedBy;
		this.approveTime = approveTime;
		this.associate = associate;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getCheckinTime() {
		return checkinTime;
	}

	public void setCheckinTime(LocalDateTime checkinTime) {
		this.checkinTime = checkinTime;
	}

	public LocalDateTime getCheckoutTime() {
		return checkoutTime;
	}

	public void setCheckoutTime(LocalDateTime checkoutTime) {
		this.checkoutTime = checkoutTime;
	}

	public Manager getApprovedBy() {
		return approvedBy;
	}

	public void setApprovedBy(Manager approvedBy) {
		this.approvedBy = approvedBy;
	}

	public LocalDateTime getApproveTime() {
		return approveTime;
	}

	public void setApproveTime(LocalDateTime approveTime) {
		this.approveTime = approveTime;
	}

	public Associate getAssociateId() {
		return associate;
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
		result = prime * result + ((approveTime == null) ? 0 : approveTime.hashCode());
		result = prime * result + ((approvedBy == null) ? 0 : approvedBy.hashCode());
		result = prime * result + ((associate == null) ? 0 : associate.hashCode());
		result = prime * result + ((checkinTime == null) ? 0 : checkinTime.hashCode());
		result = prime * result + ((checkoutTime == null) ? 0 : checkoutTime.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Checkin other = (Checkin) obj;
		if (approveTime == null) {
			if (other.approveTime != null)
				return false;
		} else if (!approveTime.equals(other.approveTime))
			return false;
		if (approvedBy == null) {
			if (other.approvedBy != null)
				return false;
		} else if (!approvedBy.equals(other.approvedBy))
			return false;
		if (associate == null) {
			if (other.associate != null)
				return false;
		} else if (!associate.equals(other.associate))
			return false;
		if (checkinTime == null) {
			if (other.checkinTime != null)
				return false;
		} else if (!checkinTime.equals(other.checkinTime))
			return false;
		if (checkoutTime == null) {
			if (other.checkoutTime != null)
				return false;
		} else if (!checkoutTime.equals(other.checkoutTime))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Checkin [id=" + id + ", checkinTime=" + checkinTime + ", checkoutTime=" + checkoutTime + ", approvedBy="
				+ approvedBy + ", approveTime=" + approveTime + ", associate=" + associate + "]";
	}

	@Override
	public void validate() throws SmsCustomException {
		// TODO Validate your members.

	}

}
