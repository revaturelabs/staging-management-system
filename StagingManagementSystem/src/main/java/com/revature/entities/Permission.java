package com.revature.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "PERMISSIONS")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Permission {
	
	@Id
	@Column(name = "PERMISSION_ID")
	@SequenceGenerator(name="PERMISSION_ID_SEQ", sequenceName="PERMISSION_ID_SEQ")
	@GeneratedValue(generator="PERMISSION_ID_SEQ", strategy=GenerationType.AUTO)
	private long id;
	
	@Column(name = "PERMISSION_LEVEL")
	private String level;
}
