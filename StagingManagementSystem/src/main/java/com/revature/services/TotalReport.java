package com.revature.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.revature.entities.Associate;


@Service
public class TotalReport {
	
	public class TotalData {
	private String batchName;
	private long totalAvailable;
	private long totalUnavailable;
	private Collection<String> names = new ArrayList<String>();
	private Collection<String> portLinks = new ArrayList<String>();
	
	
	public Collection<String> getNames() {
		return names;
	}
	public void setNames(Collection<String> names) {
		this.names = names;
	}
	public Collection<String> getPortLinks() {
		return portLinks;
	}
	public void setPortLinks(Collection<String> portLinks) {
		this.portLinks = portLinks;
	}
	public String getBatchName() {
		return batchName;
	}
	public void setBatchName(String batchName) {
		this.batchName = batchName;
	}
	public long getTotalAvailable() {
		return totalAvailable;
	}
	public void setTotalAvailable(long totalAvailable) {
		this.totalAvailable = totalAvailable;
	}
	public long getTotalUnavailable() {
		return totalUnavailable;
	}
	public void setTotalUnavailable(long totalUnavailable) {
		this.totalUnavailable = totalUnavailable;
	}
	public TotalData(String batchName) {
		super();
		this.batchName = batchName;
	}
	public void addName(String name){
		names.add(name);
	}
	public void addPortLink(String portLink){
		portLinks.add(portLink);
	}
}
	private Map<String, TotalData> totaldata = new HashMap<String, TotalData>();
	
	public void addBatch(Associate associate){
		String batchName = associate.getBatch().getBatchType().getValue();
		String name = associate.getName();
		String portLink = associate.getPortfolioLink();
	
		TotalData t = totaldata.get(batchName);
		if(t == null){
			totaldata.put(batchName, t = new TotalData(batchName));
		}
		if(associate.getLockedTo() != null){}
		boolean totalUnavialable = associate.isActive() && 
				associate.getLockedTo() != null &&
				associate.getLockedTo().isPriority();
		if(totalUnavialable == true ){
			t.totalUnavailable++;
			t.addName(name);
			t.addPortLink(portLink);
		}
		boolean totalAvailable = associate.isActive(); 
		if(totalAvailable == true){
			t.totalAvailable++;
			t.addName(name);
			t.addPortLink(portLink);
		}
	}
	
	public Collection<TotalData> returnTotalData(){
		return totaldata.values();		
	}

	public Map<String, TotalData> getTotaldata() {
		return totaldata;
	}

	public void setTotaldata(Map<String, TotalData> totaldata) {
		this.totaldata = totaldata;
	}

	public TotalReport() {
		super();
	}
	
	public Collection<TotalData> process(Set<Associate> associates){
		this.totaldata = new HashMap<String, TotalData>();
		for(Associate a : associates){
			this.addBatch(a);
		}
		return returnTotalData();
	}
}
