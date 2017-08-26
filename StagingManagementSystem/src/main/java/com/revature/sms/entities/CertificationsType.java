package com.revature.sms.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="CERTIFICATION_TYPE")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

public class CertificationsType {
	

	@Id
	@Column
     private String type_of_cert;
     @ManyToOne(fetch= FetchType.LAZY)
     @JoinColumn(name = "Cert_Id")
     private Certifications certification;
	public CertificationsType() {
		super();

	}
	public CertificationsType(String type_of_cert, Certifications certification) {
		super();
		this.type_of_cert = type_of_cert;
		this.certification = certification;
	}
	public String getType_of_cert() {
		return type_of_cert;
	}
	public void setType_of_cert(String type_of_cert) {
		this.type_of_cert = type_of_cert;
	}
	public Certifications getCertification() {
		return certification;
	}
	public void setCertification(Certifications certification) {
		this.certification = certification;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((certification == null) ? 0 : certification.hashCode());
		result = prime * result + ((type_of_cert == null) ? 0 : type_of_cert.hashCode());
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
		CertificationsType other = (CertificationsType) obj;
		if (certification == null) {
			if (other.certification != null)
				return false;
		} else if (!certification.equals(other.certification))
			return false;
		if (type_of_cert == null) {
			if (other.type_of_cert != null)
				return false;
		} else if (!type_of_cert.equals(other.type_of_cert))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Certifications_Type [type_of_cert=" + type_of_cert + ", certification=" + certification + "]";
	}
     
   
     
}
