package com.revature.classes;

import java.util.Set;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/*
 * Created On 5/11/2017
 */
@Entity
@Table(name = "ASSOCIATEINFO")
public class AssociateInfo {
	/*
	 * PK: ASSOCIATEID (Auto Generated) FK/Reference: BatchInfo.TrainingName
	 * AssociateID NUMBER CONSTRAINTS: NOT NULL ASSOCIATENAME VARCHAR(30)
	 * CONSTRAINTS: NOT NULL STATUS VARCHAR(25) CONSTRAINTS: NOT NULL
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int AssociateID;

	@Column(length = 30, nullable = false)
	private String AssociateName;

	@Column(length = 25, nullable = false)
	private String Status;

	@ManyToOne
	@JoinColumn(name = "TrainingName")
	private BatchInfo batch;

	/*
	 * Begin Constructor Methods
	 */	
	public AssociateInfo(){}
	
	public AssociateInfo(String associateName, String status, BatchInfo batch)
	{
		AssociateName = associateName;
		Status = status;
		this.batch = batch;
	}
	
	
	public AssociateInfo(int associateID, String associateName, String status, BatchInfo batch) {
		super();
		AssociateID = associateID;
		AssociateName = associateName;
		Status = status;
		this.batch = batch;
	}
	
	/*
	 * Begin Setter/Getter Methods
	 */

	public BatchInfo getBatch() {
		return batch;
	}

	public void setBatch(BatchInfo batch) {
		this.batch = batch;
	}



	/*public Set<ClientInfo> getClients() {
		return clients;
	}

	public void setClients(Set<ClientInfo> clients) {
		this.clients = clients;
	}*/

	public int getAssociateID() {
		return AssociateID;
	}

	public void setAssociateID(int associateID) {
		AssociateID = associateID;
	}

	public String getAssociateName() {
		return AssociateName;
	}

	public void setAssociateName(String associateName) {
		AssociateName = associateName;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}
	
	/*
	 * End Setter/Getter Methods
	 */
	/*
	 * Inserting Testing toString
	 */

	@Override
	public String toString() {
		return "AssociateInfo [AssociateID=" + AssociateID + ", AssociateName=" + AssociateName + ", Status=" + Status
				+ ", batch=" + batch + "]";
	}
	/*
	 * End toString
	 */
}
