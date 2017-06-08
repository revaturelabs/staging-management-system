package com.revature.entities;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters.LocalDateTimeConverter;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.revature.config.SmsSettings;
import com.revature.exceptions.SmsCustomException;
import com.revature.markers.SmsValidatable;

@Entity
@Table(name = "BATCHES")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Batch implements SmsValidatable {

	transient private static SmsSettings settings = SmsSettings.getInstance();

	@Id
	@Column(name = "BATCH_ID")
	@SequenceGenerator(name = "BATCH_ID_SEQ", sequenceName = "BATCH_ID_SEQ")
	@GeneratedValue(generator = "BATCH_ID_SEQ", strategy = GenerationType.SEQUENCE)
	private long id;

	@ManyToOne
	@JoinColumn(name = "BATCH_TYPE_ID")
	private BatchType batchType;

	@Column(name = "BATCH_START_DATE")
	@Convert(converter = LocalDateTimeConverter.class)
	private LocalDateTime startDate;

	@Column(name = "BATCH_END_DATE")
	@Convert(converter = LocalDateTimeConverter.class)
	private LocalDateTime endDate;

	@ManyToOne
	@JoinColumn(name = "LOCATION_ID")
	private Location location;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "BATCH_TRAINER", joinColumns = @JoinColumn(name = "BATCH_ID"), inverseJoinColumns = @JoinColumn(name = "TRAINER_ID"))
	private Set<Trainer> trainers;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "batch")
	private Set<Associate> associates;

	public Batch() {
		super();
		this.trainers = new HashSet<Trainer>();
		this.associates = new HashSet<Associate>();
	}

	public Batch(long id, BatchType batchType, LocalDateTime startDate, LocalDateTime endDate, Location location,
			Set<Trainer> trainers, Set<Associate> associates) {
		super();
		this.id = id;
		this.batchType = batchType;
		this.startDate = startDate;
		this.endDate = endDate;
		this.location = location;
		this.trainers = trainers;
		this.associates = associates;
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

	public LocalDateTime getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDateTime startDate) {
		this.startDate = startDate;
	}

	public LocalDateTime getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDateTime endDate) {
		this.endDate = endDate;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public Set<Trainer> getTrainers() {
		return trainers;
	}

	public void setTrainers(Set<Trainer> trainers) {
		this.trainers = trainers;
	}

	public Set<Associate> getAssociates() {
		return associates;
	}

	public void setAssociates(Set<Associate> associates) {
		this.associates = associates;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((associates == null) ? 0 : associates.hashCode());
		result = prime * result + ((batchType == null) ? 0 : batchType.hashCode());
		result = prime * result + ((endDate == null) ? 0 : endDate.hashCode());
		result = prime * result + ((location == null) ? 0 : location.hashCode());
		result = prime * result + ((startDate == null) ? 0 : startDate.hashCode());
		result = prime * result + ((trainers == null) ? 0 : trainers.hashCode());
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
		Batch other = (Batch) obj;
		if (associates == null) {
			if (other.associates != null)
				return false;
		} else if (!associates.equals(other.associates))
			return false;
		if (batchType == null) {
			if (other.batchType != null)
				return false;
		} else if (!batchType.equals(other.batchType))
			return false;
		if (endDate == null) {
			if (other.endDate != null)
				return false;
		} else if (!endDate.equals(other.endDate))
			return false;
		if (location == null) {
			if (other.location != null)
				return false;
		} else if (!location.equals(other.location))
			return false;
		if (startDate == null) {
			if (other.startDate != null)
				return false;
		} else if (!startDate.equals(other.startDate))
			return false;
		if (trainers == null) {
			if (other.trainers != null)
				return false;
		} else if (!trainers.equals(other.trainers))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Batch [id=" + id + ", batchType=" + batchType + ", startDate=" + startDate + ", endDate=" + endDate
				+ ", location=" + location + ", trainers=" + trainers + ", associates=" + associates + "]";
	}

	@Override
	public void validate() throws SmsCustomException {
		// TODO Validate your members.

	}

}
