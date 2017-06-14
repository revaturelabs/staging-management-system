package com.revature.services;

import java.time.LocalDateTime;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.entities.Associate;
import com.revature.entities.Checkin;

@Service
public class CheckinReportServiceImpl {
  
  /**
   * This class represents count totals for a given day.
   * @author jozse
   *
   */
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
      hourCount += 1;
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
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;

      DailyReport that = (DailyReport) o;

      if (hourCount != that.hourCount) return false;
      if (hourEstimate != that.hourEstimate) return false;
      return time != null ? time.equals(that.time) : that.time == null;
    }

    @Override
    public int hashCode() {
      int result = time != null ? time.hashCode() : 0;
      result = 31 * result + hourCount;
      result = 31 * result + hourEstimate;
      return result;
    }

    @Override
    public int compareTo(DailyReport other) {
      return this.getTime().compareTo(other.getTime());
    }
  }
  
  @Autowired
  AssociateService associateService;
  
  Map<String, DailyReport> reports;
  Set<Associate> associates;
  
  public CheckinReportServiceImpl(){
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
      reports.put(getKey(checkin), new DailyReport(checkin, hourEstimate));
    }
  }
  
  public void addToMap(String key, Map<String, ArrayList<String>> map, String name) {
    if(reports.containsKey(key)){
      map.get(key).add(name);
    }
    else {
      ArrayList<String> names = new ArrayList<String>();
      map.put(key, names);
      names.add(name);
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
    	
     if(a.getBatch() != null && a.isTrackedOnDate(date))
     {    		
        total += 1;
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
    
    for(Checkin c : checkins){
      this.addCheckin(c);
    }
    return convertAndOrder(reports);
  }
  
  public ArrayList<ArrayList<String>> getCheckinNamesOnDate() {
    
    return null;
  }

  private ArrayList<DailyReport> convertAndOrder(Map<String, DailyReport> reports2) {
    ArrayList<DailyReport> drs = new ArrayList<DailyReport>();
    drs.addAll(reports2.values());
    Collections.sort(drs);
    return drs;
  }
}
