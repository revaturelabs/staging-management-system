package com.revature.classes;

import javax.persistence.*;

/*
 * Created On 5/25/2017
 */

@Entity
@Table(name = "ASSOCIATE_CLIENT")
public class AssociateClient {
	
	@Id
	private int AssociateID;

	@Column(length = 19, nullable = false)
	private int ClientID;

	public int getAssociateID() {
		return AssociateID;
	}

	public void setAssociateID(int associateID) {
		AssociateID = associateID;
	}

	public int getClientID() {
		return ClientID;
	}

	public void setClientID(int clientId) {
		ClientID = clientId;
	}

	@Override
	public String toString() {
		return "AssociateClient [AssociateID=" + AssociateID + ", ClientID=" + ClientID + "]";
	}

}
