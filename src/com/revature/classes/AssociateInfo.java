
package com.revature.classes;

import java.util.Set;
import javax.persistence.*;

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
	private long AssociateID;

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
	
	public AssociateInfo(String associateName, String status, BatchInfo batch, Set<ClientInfo> clients)
	{
		AssociateName = associateName;
		Status = status;
		this.batch = batch;
		this.clients = clients;
	}
	
	public AssociateInfo(long associateID, String associateName, String status, BatchInfo batch,
			Set<ClientInfo> clients) {
		super();
		AssociateID = associateID;
		AssociateName = associateName;
		Status = status;
		this.batch = batch;
		this.clients = clients;
	}
	
	/*
	 * Create Many to Many Relationship with ClientInfo table
	 */
	// Name to refer to on ClientInfo on line 84: `@ManyToMany(fetch =
	// FetchType.LAZY, mappedBy = "clients")`

	// Assign Many to Many
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	// Create Additional Table populated from AssociateInfo with name
	// "Associate_Clients", link foreign key wiith inverseJoinColumns
	@JoinTable(name = "Associate_Clients", joinColumns = {
			@JoinColumn(name = "AssociateID", nullable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "ClientID", nullable = false, updatable = false) })
		private Set<ClientInfo> clients;
/*
	 * End Many to Many Relationship
	 */
	
	/*
	 * Begin Setter/Getter Methods
	 */

	public BatchInfo getBatch() {
		return batch;
	}

	public void setBatch(BatchInfo batch) {
		this.batch = batch;
	}



	public Set<ClientInfo> getClients() {
		return clients;
	}

	public void setClients(Set<ClientInfo> clients) {
		this.clients = clients;
	}

	public long getAssociateID() {
		return AssociateID;
	}

	public void setAssociateID(long associateID) {
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
				+ ", batch=" + batch + ", clients=" + clients + "]";
	}
	/*
	 * End toString
	 */
}