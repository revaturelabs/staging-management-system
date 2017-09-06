package com.revature.sms.repositories;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.revature.sms.entities.AttendanceRecord;

public interface AttendanceReportRepo extends JpaRepository<AttendanceRecord, Long> {

  @Query(nativeQuery = true, value = "SELECT * FROM TABLE(GET_ATTENDANCE_REPORT (:startDate, :endDate))")
  Set<AttendanceRecord> getAssociatesInStaggingOn(@Param(value = "startDate") String startDate, @Param(value = "endDate") String endDate);
}
