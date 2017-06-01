package com.revature.classes;

import java.sql.Date;
import java.util.Set;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.fasterxml.jackson.annotation.JsonIgnore;
/*
 * Created On 5/11/2017
 */
@Entity
@Table(name = "BATCHINFO")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class BatchInfo {
	/*
	 * PK: TrainingName TRAININGNAME VARCHAR(30) CONSTRAINTS: NOT NULL LOCATION
	 * VARCHAR(150) CONTSTRAINTS: NOT NULL; TRAINER VARCHAR(25) CONSTRAINTS: NOT
	 * NULL STARTDATE DATE CONSTRAINTS: NOT NULL ENDDATE DATE CONSTRAINTS: NOT
	 * NULL
	 */
	@Id
	@Column(length = 30)
	private String TrainingName;

	@Column(length = 100, nullable = false)
	private String Location;

	@Column(length = 30, nullable = false)
	private String Trainer;

	@Column(nullable = false)
	private Date StartDate;

	@Column(nullable = false)
	private Date EndDate;

	@Column(length = 25, nullable = false)
	private String Type;

	/*
	 * Begin Constructor Methods
	 */
	public BatchInfo()
	{
		
	}

	public BatchInfo(String trainingName, String location, String trainer, Date startDate, Date endDate, String type) {
		super();
		TrainingName = trainingName;
		Location = location;
		Trainer = trainer;
		StartDate = startDate;
		EndDate = endDate;
		Type = type;
	}

	/*
	 * End Constructor Method
	 */
	/*
	 * Begin Setter/Getter Methods
	 */
	/*
	 * Start One to Many Relationship (1)Batch:(M):Associates
	 */
	// Initialize 1:M
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, cascade = (CascadeType.ALL))
	@JoinColumn(name = "TrainingName")
	private Set<AssociateInfo> associates; 

	public Set<AssociateInfo> getAssociates() {

		return associates;
	}

	public void setAssociates(Set<AssociateInfo> associates) 
	{
		this.associates = associates;
	}


	/*
	 * End One to Many Relationship
	 */

	public String getTrainingName() 
	{
		return TrainingName;
	}

	public void setTrainingName(String trainingName) 
	{
		TrainingName = trainingName;
	}

	public String getLocation() {
		return Location;
	}

	public void setLocation(String location) 
	{
		Location = location;
	}

	public String getTrainer() {
		return Trainer;
	}

	public void setTrainer(String trainer) 
	{
		Trainer = trainer;
	}

	public Date getStartDate() {
		return StartDate;
	}

	public void setStartDate(Date startDate) 
	{
		StartDate = startDate;
	}

	public Date getEndDate()
	{
		return EndDate;
	}

	public void setEndDate(Date endDate)
	{
		EndDate = endDate;
	}

	public String getType() {
		return Type;
	}

	public void setType(String type) {
		Type = type;
	}
	/*
	 * End Setter/Getter Methods
	 */
	/*
	 * Inserting Testing toString
	 */

	@Override
	public String toString() 
	{
		return "BatchInfo [TrainingName=" + TrainingName + ", Location=" + Location + ", Trainer=" + Trainer
				+ ", StartDate=" + StartDate + ", EndDate=" + EndDate + ", Type=" + Type + "]";
	}
	/*
	 * End toString
	 */
}
