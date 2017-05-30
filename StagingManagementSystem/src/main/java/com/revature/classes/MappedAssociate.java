package com.revature.classes;

import java.util.List;

public class MappedAssociate 
{
	private List associateId;
    private String status;
    private long clientId;
    
	public List getAssociateId() {
		return associateId;
	}
	public void setAssociateIds(List associateId) {
		this.associateId = associateId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public long getClientId() {
		return clientId;
	}
	public void setClientId(long clientId) {
		this.clientId = clientId;
	}
	
	@Override
	public String toString() {
		return "MappedAssociate [associateIds=" + associateId + ", status=" + status + ", client=" + clientId + "]";
	}
	
}
