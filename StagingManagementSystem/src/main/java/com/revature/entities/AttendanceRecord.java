package com.revature.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;

import com.revature.util.LocalDateTimeConverter;

@Entity
@Table(name = "ATTENDANCE_TABLE")
public class AttendanceRecord {

  @Id
  @Column
  private long id;

  @Column(name = "ON_DATE")
  @Convert(converter = LocalDateTimeConverter.class)
  private LocalDateTime time;
  
  @Column(name = "CHECKED_IN")
  private int hourCount;    // This now represents staggingAssociateChechedInCount @LegacyVarName.
  @Column(name = "TOTAL")
  private int hourEstimate; // This now represents staggingAssociateTotal @LegacyVarName.
 
  @Override
  public String toString() {
    return "AttendanceRecord [time=" + time + ", hourCount=" + hourCount + ", hourEstimate=" + hourEstimate + "]";
  }
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + hourCount;
    result = prime * result + hourEstimate;
    result = prime * result + ((time == null) ? 0 : time.hashCode());
    return result;
  }
  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    AttendanceRecord other = (AttendanceRecord) obj;
    if (hourCount != other.hourCount)
      return false;
    if (hourEstimate != other.hourEstimate)
      return false;
    if (time == null) {
      if (other.time != null)
        return false;
    } else if (!time.equals(other.time))
      return false;
    return true;
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
  public long getId() {
    return id;
  }
  public void setId(long id) {
    this.id = id;
  }
}
