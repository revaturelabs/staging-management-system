package com.revature.entities;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "CHECKINS")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Checkin {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CHECKIN_SEQ")
	@SequenceGenerator(name = "CHECKIN_SEQ", sequenceName = "SEQUENCE_CHECKIN_ID_SEQ")
	@Column
	private Long id;
	
	@Column(name = "CHECKIN_TIME")
	private LocalDateTime checkinTime;
	
	@Column(name = "CHECKOUT_TIME")
	private LocalDateTime checkoutTime;
	
	@OneToMany(fetch = FetchType.EAGER)
	@Column(name = "APPROVED_BY")
	@JoinColumn(name = "MANAGER_ID")
	private Manager approvedBy;
	
	@Column(name = "APPROVE_TIME")
	private LocalDateTime approveTime;
	
	@OneToOne(mappedBy = "Associates")
	@Column(name = "ASSOCIATE_ID")
	@JoinColumn(name = "ASSOCIATES_ID")
	private Associate associateId;

	public Checkin() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Checkin(Long id, LocalDateTime checkinTime, LocalDateTime checkoutTime, Manager approvedBy,
			LocalDateTime approveTime, Associate associateId) {
		super();
		this.id = id;
		this.checkinTime = checkinTime;
		this.checkoutTime = checkoutTime;
		this.approvedBy = approvedBy;
		this.approveTime = approveTime;
		this.associateId = associateId;
	}

	public Long getId() {
		return id;
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
		return associateId;
	}

	public void setAssociateId(Associate associateId) {
		this.associateId = associateId;
	}
	
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((approveTime == null) ? 0 : approveTime.hashCode());
		result = prime * result + ((approvedBy == null) ? 0 : approvedBy.hashCode());
		result = prime * result + ((associateId == null) ? 0 : associateId.hashCode());
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
		if (associateId == null) {
			if (other.associateId != null)
				return false;
		} else if (!associateId.equals(other.associateId))
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
				+ approvedBy + ", approveTime=" + approveTime + ", associateId=" + associateId + "]";
	}
	
	
}
