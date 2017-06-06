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
import com.revature.config.SmsSettings;
import com.revature.exceptions.SmsCustomException;
import com.revature.markers.SmsValidatable;

@Entity
@Table(name = "ASSOCIATES")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Associate implements SmsValidatable {

	transient private static SmsSettings settings = SmsSettings.getInstance();
	
	@Id
	@Column(name = "ASSOCIATE_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ASSOCIATE_ID_SEQ")
	@SequenceGenerator(name = "ASSOCIATE_ID_SEQ", sequenceName = "ASSOCIATE_ID_SEQ")
	private Long id;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "CREDENTIAL_ID")
	private Credential credential;

	@Column(name = "ASSOCIATE_NAME")
	private String name;

	@Column(name = "ASSOCIATE_PORTFOLIO_LINK")
	private String portfolioLink;

	@ManyToOne
	@JoinColumn(name = "BATCH_ID")
	private Batch batch;

	@Column(name = "ASSOCIATE_ACTIVE")
	private short active;

	@Column(name = "CLIENT_ID")
	private Long lockedTo;

	public Associate() {
		super();
	}

	public Associate(Long id, Credential credential, String name, String portfolioLink, Batch batch, short active,
			Long lockedTo) {
		super();
		this.id = id;
		this.credential = credential;
		this.name = name;
		this.portfolioLink = portfolioLink;
		this.batch = batch;
		this.active = active;
		this.lockedTo = lockedTo;
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

	public short getActive() {
		return active;
	}

	public void setActive(short active) {
		this.active = active;
	}

	public Long getLockedTo() {
		return lockedTo;
	}

	public void setLockedTo(Long lockedTo) {
		this.lockedTo = lockedTo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + active;
		result = prime * result + ((batch == null) ? 0 : batch.hashCode());
		result = prime * result + ((credential == null) ? 0 : credential.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((lockedTo == null) ? 0 : lockedTo.hashCode());
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
		if (getClass() != obj.getClass())
			return false;
		Associate other = (Associate) obj;
		if (active != other.active)
			return false;
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
		if (lockedTo == null) {
			if (other.lockedTo != null)
				return false;
		} else if (!lockedTo.equals(other.lockedTo))
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
				+ portfolioLink + ", batch=" + batch + ", active=" + active + ", lockedTo=" + lockedTo + "]";
	}

	@Override
	public void validate() throws SmsCustomException {
		// TODO Validate your members.
	}

}
