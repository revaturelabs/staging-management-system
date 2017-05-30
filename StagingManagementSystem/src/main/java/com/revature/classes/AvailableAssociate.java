package com.revature.classes;

import java.util.List;

public class AvailableAssociate 
{
	private List associateId;
	private long client;
	
	public List getAssociateId() {
		return associateId;
	}
	public void setAssociateId(List associateId) {
		this.associateId = associateId;
	}
	public long getClient() {
		return client;
	}
	public void setClient(long client) {
		this.client = client;
	}
	@Override
	public String toString() {
		return "AvailableAssociate [associateId=" + associateId + ", client=" + client + "]";
	}
	
}
