package com.revature.entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name="BATCHES")
public class Batch {

	@Id
	@Column(name="BATCH_ID")
	private Long id;

	@ManyToOne
	@JoinColumn(name="BATCH_TYPE_ID")
	private BatchType batchType;

	@Column(name="START_DATE")
	private LocalDate startDate;

	@Column(name="END_DATE")
	private LocalDate endDate;

	/*
	@ManyToOne
	@JoinColumn(name="LOCATION_ID")
	private Location location;*/

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "BATCH_TRAINER", joinColumns = @JoinColumn(name="BATCH_ID"), inverseJoinColumns = @JoinColumn(name="TRAINER_ID"))
	private Trainer trainer;

	@OneToMany(mappedBy = "BATCH_ID")
	private List<Associate> associates;

	public Batch(){
		//  //
	}

	public Batch(Long id, BatchType batchType, LocalDate startDate, LocalDate endDate, Trainer trainer, List<Associate> associates) {
		this.id = id;
		this.batchType = batchType;
		this.startDate = startDate;
		this.endDate = endDate;
		this.trainer = trainer;
		this.associates = associates;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Batch batch = (Batch) o;

		if (id != null ? !id.equals(batch.id) : batch.id != null) return false;
		if (batchType != null ? !batchType.equals(batch.batchType) : batch.batchType != null) return false;
		if (startDate != null ? !startDate.equals(batch.startDate) : batch.startDate != null) return false;
		if (endDate != null ? !endDate.equals(batch.endDate) : batch.endDate != null) return false;
		if (trainer != null ? !trainer.equals(batch.trainer) : batch.trainer != null) return false;
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

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public Trainer getTrainer() {
		return trainer;
	}

	public void setTrainer(Trainer trainer) {
		this.trainer = trainer;
	}

	public List<Associate> getAssociates() {
		return associates;
	}

	public void setAssociates(List<Associate> associates) {
		this.associates = associates;
	}
}
