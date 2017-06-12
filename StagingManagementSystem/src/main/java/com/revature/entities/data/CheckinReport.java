package com.revature.entities.data;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.entities.Associate;
import com.revature.entities.Checkin;
import com.revature.entities.Job;
import com.revature.services.AssociateService;
import com.revature.services.JobService;

@Service
public class CheckinReport {
  
  public class DailyReport implements Comparable<DailyReport>{
    LocalDateTime time;
    int hourCount;
    int hourEstimate;

    public DailyReport(Checkin checkin, int hourEstimate){
      super();
      this.hourEstimate = hourEstimate;
      this.time = checkin.getCheckinTime();
      this.hourCount = 0;
      addCheckin(checkin);
    }
    
    public void addCheckin(Checkin checkin){
      hourCount += ChronoUnit.HOURS.between(checkin.getCheckinTime(), checkin.getCheckoutTime());
    }
    
    public LocalDateTime getTime() {
      return time;
    }

    public void setTime(LocalDateTime time) {
      this.time = time;
    }

    public int getHourCount() {
      return hourCount;
    }

    public void setHourCount(int hourCount) {
      this.hourCount = hourCount;
    }

    public int getHourEstimate() {
      return hourEstimate;
    }

    public void setHourEstimate(int hourEstimate) {
      this.hourEstimate = hourEstimate;
    }

    @Override
    public int compareTo(DailyReport other) {
      return this.getTime().compareTo(other.getTime());
    }
  }
  
  @Autowired
  AssociateService associateService;
  @Autowired
  JobService jobService;
  
  Map<String, DailyReport> reports;
  Set<Associate> associates;
  
  public CheckinReport(){
    super();
  }

  /**
   * If a previous-checkin has been processed from the same day then checkin is added to that 
   * reports object. Else a new Reports object is made and added to the map.
   */
  public void addCheckin(Checkin checkin){
    String key = getKey(checkin);
    if(reports.containsKey(key)){
      reports.get(key).addCheckin(checkin);
    }
    else {
      int hourEstimate = calculateHourEstimate(checkin.getCheckinTime());
      System.out.println("Estamate: " + hourEstimate);
      reports.put(getKey(checkin), new DailyReport(checkin, hourEstimate));
    }
  }
  
  /**
   * Returns a string in the format YYYY-MM-DD
   */
  private String getKey(Checkin checkin) {
    return checkin.getCheckinTime().toString().substring(0,10);
  }
  
  /**
   * This function assumes everyone works eight hours a day. Then calculates total work hours
   * for the given date based of of that.
   */
  private int calculateHourEstimate(LocalDateTime date){
    int total = 0;
    for(Associate a : associates){
    	
     if(a.isTrackedOnDate(date))
     {    		
        total += 8;
     }
    }
    return total;
  }
  
  
  public Map<String, DailyReport> getReports() {
    return reports;
  }
  
  public void setReports(Map<String, DailyReport> reports) {
    this.reports = reports;
  }

  
  /**
   * Call this function to empty hash map and re-pull associates from the database.
   * @return 
   */
  public ArrayList<DailyReport> process(Set<Checkin> checkins) {
    this.reports = new HashMap<String, DailyReport>();
    associates = associateService.getAll();
    //TODO: making a db call for every associate is inefficient should do a join call in associates.
    for(Associate a : associates){
      a.setJobs(jobService.findByAssociate(a));
    }
    
    for(Checkin c : checkins){
      this.addCheckin(c);
    }
    return convertAndOrder(reports);
  }

  private ArrayList<DailyReport> convertAndOrder(Map<String, DailyReport> reports2) {
    ArrayList<DailyReport> drs = new ArrayList<DailyReport>();
    drs.addAll(reports2.values());
    Collections.sort(drs);
    return drs;
  }
}
