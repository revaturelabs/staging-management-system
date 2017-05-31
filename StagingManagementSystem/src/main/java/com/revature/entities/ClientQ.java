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
public class ClientQ {
	@Id
	@Column(name="CLIENT_QUESTION_ID")
	@SequenceGenerator(name="CLIENT_QUESTION_ID_SEQ", sequenceName="CLIENT_QUESTION_ID_SEQ")
	@GeneratedValue(generator="CLIENT_QUESTION_ID_SEQ", strategy=GenerationType.AUTO)
	private long ClientQuestionId;
	
//	@ManyToOne(fetch=FetchType.LAZY)
//	@JoinColumn(name="CLIENT_ID")
//	private Client client;
//	
//	@ManyToOne(fetch=FetchType.LAZY)
//	@JoinColumn(name="INTERVIEW_QUESTION_ID")
//	private Interview interview;
//	
//	@ManyToOne(fetch=FetchType.LAZY)
//	@JoinColumn(name="ASSOCIATE_ID")
//	private Associate associate;
	
}
