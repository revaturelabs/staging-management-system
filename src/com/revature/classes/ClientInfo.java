package com.revature.classes;

import javax.persistence.*;

/*
 * Created On 5/11/2017
 */
@Entity
@Table(name = "CLIENTINFO")
public class ClientInfo {
	/*
	 * PK: ClientID (Auto Generated) ClientID NUMBER CONSTRAINTS: NOT NULL NAME
	 * VARCHAR(25) CONSTRAINTS: NOT NULL LOCATION VARCHAR(150) CONSTRAINTS: NOT
	 * NULL
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long ClientID;

	@Column(length = 25, nullable = false)
	private String Name;

	@Column(length = 150, nullable = false)
	private String Location;

/*	@ManyToOne
	@JoinColumn(name = "ClientID", referencedColumnName = "ClientID", insertable = true, updatable = false)
	private AssociateInfo associate; */

	/*
	 * Constructor Methods
	 */
	public ClientInfo() {
	}
	
	public ClientInfo(String name, String location){
		Name = name;
		Location = location;
	}

	public ClientInfo(long clientID, String name, String location) {
		super();
		ClientID = clientID;
		Name = name;
		Location = location;
	}
	/*
	 * End of Constructor Methods
	 */

	/*
	 * Initialize Getters and Setters
	 */
	public long getClientID() {
		return ClientID;
	}

	public void setClientID(long clientID) {
		ClientID = clientID;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getLocation() {
		return Location;
	}

	public void setLocation(String location) {
		Location = location;
	}

	/*
	 * Many Clients Can Have Many Associates
	 * 
	 * M2M Relationship mapped by AssociateInfo Line 51: ` private
	 * Set<ClientInfo> clients; `
	 */
	/*public AssociateInfo getAssociate() {
		return associate;
	}

	public void setAssociate(AssociateInfo associate) {
		this.associate = associate;
	} */
	/*
	 * End of M2M Relationship for AssociateInfo::ClientInfo End of
	 * Getter/Setter Methods
	 */

	/*
	 * Generic toString testing
	 */
	@Override
	public String toString() {
		return "ClientInfo [ClientID=" + ClientID + ", Name=" + Name + ", Location=" + Location + "]";
	}

}
