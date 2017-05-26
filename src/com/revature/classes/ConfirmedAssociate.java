package com.revature.classes;

import java.util.List;

public class ConfirmedAssociate 
{
	private List associateId;

	public List getAssociateId() {
		return associateId;
	}

	public void setAssociateId(List associateId) {
		this.associateId = associateId;
	}

	@Override
	public String toString() {
		return "ConfirmedAssociate [associateId=" + associateId + "]";
	}
	
}
