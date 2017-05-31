package com.revature.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "MANAGERS")
public class Manager {

	@Id
	@Column(name = "MANAGER_ID")
	@SequenceGenerator(name="MANAGER_ID_SEQ", sequenceName="MANAGER_ID_SEQ")
	@GeneratedValue(generator="MANAGER_ID_SEQ", strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(name = "MANAGER_NAME")
	private String name;
	
	@OneToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name="CREDENTIAL_ID")
	private Credential credential;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="PERMISSION_ID")
	private Permission permission;

}
