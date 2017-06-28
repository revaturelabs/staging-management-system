package com.revature.services;

import java.util.Collection;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.entities.Associate;
import com.revature.entities.AttendanceRecord;
import com.revature.repositories.AttendanceReportRepo;

@Service
public class CheckinReportServiceImpl {
  
  // TODO: chache results
  @Autowired
  AttendanceReportRepo attendanceReportRepo;
  
  Set<Associate> associates;
  
  public Collection<AttendanceRecord>getReport() {
    return attendanceReportRepo.getAssociatesInStaggingOn("01-JAN-17", "01-JUL-17");
  }
}
