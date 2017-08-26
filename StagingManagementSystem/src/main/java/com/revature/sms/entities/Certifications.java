package com.revature.sms.entities;


import java.util.Date;

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
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
@Table(name="CERTIFICATIONS")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Certifications {
	
	   @Id
	   @Column
	   
	   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CERTIFICATIONS_ID_SEQ")
	    @SequenceGenerator(name = "CERTIFICATIONS_ID_SEQ", sequenceName = "CERTIFICATIONS_ID_SEQ")
	     private long Cert_id;
	   	private String Cert_type;
         private String Cert_status;
         private String Cert_filename;
         private Date Cert_testdate;
         private String Cert_comments;
//         @JoinColumn(name= "MANAGER_ID")
//         private Manager manager_id;
         @ManyToOne(fetch= FetchType.LAZY)
         @JoinColumn(name = "ASSOCIATE_ID")
       //  @JsonProperty(access = Access.WRITE_ONLY)
         private Associate associate;
         
         public Certifications() {
 			super();
 			
 		}
         

		public Certifications(long cert_id, String cert_type,String cert_status, String cert_filename, Date cert_testdate,
				String cert_comments, Associate associate) {
			super();
			Cert_id = cert_id;
			Cert_type=cert_type;
			Cert_status = cert_status;
			Cert_filename = cert_filename;
			Cert_testdate = cert_testdate;
			Cert_comments = cert_comments;
			this.associate = associate;
		}


		public long getCert_id() {
			return Cert_id;
		}

		public void setCert_id(long cert_id) {
			Cert_id = cert_id;
		}

		
		public String getCert_type() {
			return Cert_type;
		}


		public void setCert_type(String cert_type) {
			Cert_type = cert_type;
		}
		
		public String getCert_status() {
			return Cert_status;
		}

		public void setCert_status(String cert_status) {
			Cert_status = cert_status;
		}

		public String getCert_filename() {
			return Cert_filename;
		}

		public void setCert_filename(String cert_filename) {
			Cert_filename = cert_filename;
		}

		public Date getCert_testdate() {
			return Cert_testdate;
		}

		public void setCert_testdate(Date cert_testdate) {
			Cert_testdate = cert_testdate;
		}

		public String getComments() {
			return Cert_comments;
		}

		public void setComments(String comments) {
			this.Cert_comments = comments;
		}

		public Associate getAssociate_id() {
			return associate;
		}

		public void setAssociate_id(Associate associate_id) {
			this.associate = associate_id;
		}


		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((Cert_comments == null) ? 0 : Cert_comments.hashCode());
			result = prime * result + ((Cert_filename == null) ? 0 : Cert_filename.hashCode());
			result = prime * result + (int) (Cert_id ^ (Cert_id >>> 32));
			result = prime * result + ((Cert_status == null) ? 0 : Cert_status.hashCode());
			result = prime * result + ((Cert_testdate == null) ? 0 : Cert_testdate.hashCode());
			result = prime * result + ((Cert_type == null) ? 0 : Cert_type.hashCode());
			result = prime * result + ((associate == null) ? 0 : associate.hashCode());
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
			Certifications other = (Certifications) obj;
			if (Cert_comments == null) {
				if (other.Cert_comments != null)
					return false;
			} else if (!Cert_comments.equals(other.Cert_comments))
				return false;
			if (Cert_filename == null) {
				if (other.Cert_filename != null)
					return false;
			} else if (!Cert_filename.equals(other.Cert_filename))
				return false;
			if (Cert_id != other.Cert_id)
				return false;
			if (Cert_status == null) {
				if (other.Cert_status != null)
					return false;
			} else if (!Cert_status.equals(other.Cert_status))
				return false;
			if (Cert_testdate == null) {
				if (other.Cert_testdate != null)
					return false;
			} else if (!Cert_testdate.equals(other.Cert_testdate))
				return false;
			if (Cert_type == null) {
				if (other.Cert_type != null)
					return false;
			} else if (!Cert_type.equals(other.Cert_type))
				return false;
			if (associate == null) {
				if (other.associate != null)
					return false;
			} else if (!associate.equals(other.associate))
				return false;
			return true;
		}


		@Override
		public String toString() {
			return "Certifications [Cert_id=" + Cert_id + ", Cert_type=" + Cert_type + ", Cert_status=" + Cert_status
					+ ", Cert_filename=" + Cert_filename + ", Cert_testdate=" + Cert_testdate + ", Cert_comments="
					+ Cert_comments + ", associate=" + associate + "]";
		}

        
}
