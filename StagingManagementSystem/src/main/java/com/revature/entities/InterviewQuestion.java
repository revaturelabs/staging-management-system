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

@Entity
public class InterviewQuestion {
	@Id
	@Column(name="INTERVIEW_QUESTION_ID")
	@SequenceGenerator(name="INTERVIEW_QUESTION_ID_SEQ", sequenceName="INTERVIEW_QUESTION_ID_SEQ")
    @GeneratedValue(generator="INTERVIEW_QUESTION_ID_SEQ", strategy=GenerationType.AUTO)
	private long id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="BATCH_TYPE_ID")
	private Object batchType;
	
	@Column
	private String question;
}
