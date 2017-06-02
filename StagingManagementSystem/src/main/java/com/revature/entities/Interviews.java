package com.revature.entities;

import java.time.LocalDateTime;

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

@Entity
@Table(name="interviews")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Interviews {
	
	@Id
	@Column(name="interview_Id")
	@SequenceGenerator(name = "interviews_seq", sequenceName = "interviews_seq")
	@GeneratedValue(generator = "interviews_seq", strategy = GenerationType.AUTO)
	private long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "associate_Id")
	private Associate associate;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "client_Id")
	private Client client;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "interview_Status_Id")
	private InterviewStatuses interviewStatus;
	
	@Column(name = "SCHEDULED_TIME")
	private LocalDateTime scheduled;

	
	
}
