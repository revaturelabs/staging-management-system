package com.revature.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "ASSOCIATES")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Associate {

	@Id
	@Column(name = "ASSOCIATE_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ASSOCIATE_ID_SEQ")
	@SequenceGenerator(name = "ASSOCIATE_ID_SEQ", sequenceName = "ASSOCIATE_ID_SEQ")
	private Long id;

	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "CREDENTIAL_ID")
	private Credential credential;

	@Column
	private String name;

	@Column(name = "PORTFOLIO_LINK")
	private String portfolioLink;

	// @ManyToOne
	// @JoinColumn(name = "BATCH_ID")
	// private Batch batch;

	public Associate() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Associate(Long id, Credential credential, String name, String portfolioLink) {
		super();
		this.id = id;
		this.credential = credential;
		this.name = name;
		this.portfolioLink = portfolioLink;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Credential getCredential() {
		return credential;
	}

	public void setCredential(Credential credential) {
		this.credential = credential;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPortfolioLink() {
		return portfolioLink;
	}

	public void setPortfolioLink(String portfolioLink) {
		this.portfolioLink = portfolioLink;
	}

	@Override
	public String toString() {
		return "Associate [id=" + id + ", credential=" + credential + ", name=" + name + ", portfolioLink="
				+ portfolioLink + "]";
	}

}
