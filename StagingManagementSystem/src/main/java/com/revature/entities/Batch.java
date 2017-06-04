package com.revature.entities;

import java.time.LocalDateTime;
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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.revature.util.LocalDateTimeConverter;

@Entity
@Table(name = "BATCHES")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Batch {

	@Id
	@Column(name = "BATCH_ID")
	@SequenceGenerator(name = "BATCH_ID_SEQ", sequenceName = "BATCH_ID_SEQ")
	@GeneratedValue(generator = "BATCH_ID_SEQ", strategy = GenerationType.SEQUENCE)
	private Long id = 0l;

	@ManyToOne
	@JoinColumn(name = "BATCH_TYPE_ID")
	private BatchType batchType;

	@Column(name = "START_DATE")
	@Convert(converter = LocalDateTimeConverter.class)
	private LocalDateTime startDate;

	@Column(name = "END_DATE")
	@Convert(converter = LocalDateTimeConverter.class)
	private LocalDateTime endDate;

	/*
	 * @ManyToOne
	 * 
	 * @JoinColumn(name="LOCATION_ID") private Location location;
	 */

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "BATCH_TRAINER", joinColumns = @JoinColumn(name = "BATCH_ID"), inverseJoinColumns = @JoinColumn(name = "TRAINER_ID"))
	private Set<Trainer> trainer;

	@OneToMany(mappedBy = "batch")
	private Set<Associate> associates;

	public Batch() {
		// //
	}

	public Batch(Long id, BatchType batchType, LocalDateTime startDate, LocalDateTime endDate, Set<Trainer> trainer,
			Set<Associate> associates) {
		this.id = id;
		this.batchType = batchType;
		this.startDate = startDate;
		this.endDate = endDate;
		this.trainer = trainer;
		this.associates = associates;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		Batch batch = (Batch) o;

		if (id != null ? !id.equals(batch.id) : batch.id != null)
			return false;
		if (batchType != null ? !batchType.equals(batch.batchType) : batch.batchType != null)
			return false;
		if (startDate != null ? !startDate.equals(batch.startDate) : batch.startDate != null)
			return false;
		if (endDate != null ? !endDate.equals(batch.endDate) : batch.endDate != null)
			return false;
		if (trainer != null ? !trainer.equals(batch.trainer) : batch.trainer != null)
			return false;
		return associates != null ? associates.equals(batch.associates) : batch.associates == null;
	}

	@Override
	public int hashCode() {
		int result = id != null ? id.hashCode() : 0;
		result = 31 * result + (batchType != null ? batchType.hashCode() : 0);
		result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
		result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
		result = 31 * result + (trainer != null ? trainer.hashCode() : 0);
		result = 31 * result + (associates != null ? associates.hashCode() : 0);
		return result;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public Set<Trainer> getTrainers() {
		return trainer;
	}

	public void setTrainers(Set<Trainer> trainer) {
		this.trainer = trainer;
	}

	public Set<Associate> getAssociates() {
		return associates;
	}

	public void setAssociates(Set<Associate> associates) {
		this.associates = associates;
	}

	@Override
	public String toString() {
		return "Batch [id=" + id + ", batchType=" + batchType + ", startDate=" + startDate + ", endDate=" + endDate
				+ ", trainer=" + trainer + ", associates=" + associates + "]";
	}

}
