package com.revature.sms.services;

import java.util.Collection;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.sms.entities.Associate;
import com.revature.sms.entities.AttendanceRecord;
import com.revature.sms.repositories.AttendanceReportRepo;

@Service
public class CheckinReportServiceImpl {
  
  // TODO: cache results
  @Autowired
  AttendanceReportRepo attendanceReportRepo;
  
  Set<Associate> associates;
  
  public Collection<AttendanceRecord>getReport() {
    return attendanceReportRepo.getAssociatesInStaggingOn("01-JAN-17", "01-JUL-17");
  }
}
