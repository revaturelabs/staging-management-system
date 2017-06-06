package com.revature.entities;

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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.revature.exceptions.SmsCustomException;
import com.revature.markers.SmsValidatable;

@Entity
@Table(name = "ASSOCIATES")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Associate implements SmsValidatable {

	@Id
	@Column(name = "ASSOCIATE_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ASSOCIATE_ID_SEQ")
	@SequenceGenerator(name = "ASSOCIATE_ID_SEQ", sequenceName = "ASSOCIATE_ID_SEQ")
	private Long id;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "CREDENTIAL_ID")
	private Credential credential;

	@Column
	private String name;

	@Column(name = "PORTFOLIO_LINK")
	private String portfolioLink;

	@ManyToOne
	@JoinColumn(name = "BATCH_ID")
	private Batch batch;

	@Column
	private short active;

	public Associate() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Associate(Associate other){
	  this(other.id, other.credential, other.name, other.portfolioLink, other.batch);
	}

	public Associate(Long id, Credential credential, String name, String portfolioLink, Batch batch) {
		super();
		this.id = id;
		this.credential = credential;
		this.name = name;
		this.portfolioLink = portfolioLink;
		this.batch = batch;
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

	public Batch getBatch() {
		return batch;
	}

	public void setBatch(Batch batch) {
		this.batch = batch;
	}

	public boolean isActive() {
		return active != 0;
	}

	public short getActive() {
		return active;
	}

	public void setActive(short active) {
		this.active = active;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((batch == null) ? 0 : batch.hashCode());
		result = prime * result + ((credential == null) ? 0 : credential.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((portfolioLink == null) ? 0 : portfolioLink.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Associate))
			return false;
		Associate other = (Associate) obj;
		if (batch == null) {
			if (other.batch != null)
				return false;
		} else if (!batch.equals(other.batch))
			return false;
		if (credential == null) {
			if (other.credential != null)
				return false;
		} else if (!credential.equals(other.credential))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (portfolioLink == null) {
			if (other.portfolioLink != null)
				return false;
		} else if (!portfolioLink.equals(other.portfolioLink))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Associate [id=" + id + ", credential=" + credential + ", name=" + name + ", portfolioLink="
				+ portfolioLink + ", batch=" + batch + "]";
	}

	@Override
	public void validate() throws SmsCustomException {
		// TODO Validate your members.
	}

}
