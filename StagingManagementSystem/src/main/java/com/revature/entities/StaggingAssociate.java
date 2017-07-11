package com.revature.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.revature.util.LocalDateTimeConverter;

@Entity
@Table(name = "STAGGING_ASSOC_TABLE")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class StaggingAssociate {
  @Id
  @Column(name = "ASSOCIATE_ID")
  private long id;

  @Column(name = "ASSOCIATE_NAME")
  private String name;

  @Column(name = "PORTFOLIO_LINK")
  private String portfolioLink;
  
  @Column(name = "CHECKIN_DATE_TIME")
  @Convert(converter = LocalDateTimeConverter.class)
  private LocalDateTime checkinTime;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPortfolioLink() {
    return portfolioLink;
  }

  public void setPortfolioLink(String portfolioLink) {
    this.portfolioLink = portfolioLink;
  }

  public LocalDateTime getCheckinTime() {
    return checkinTime;
  }

  public void setCheckinTime(LocalDateTime checkinTime) {
    this.checkinTime = checkinTime;
  }

  @Override
  public String toString() {
    return "StaggingAssociate [id=" + id + ", name=" + name + ", portfolioLink=" + portfolioLink + ", checkinTime="
        + checkinTime + "]";
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((checkinTime == null) ? 0 : checkinTime.hashCode());
    result = prime * result + (int) (id ^ (id >>> 32));
    result = prime * result + ((name == null) ? 0 : name.hashCode());
    result = prime * result + ((portfolioLink == null) ? 0 : portfolioLink.hashCode());
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
    StaggingAssociate other = (StaggingAssociate) obj;
    if (checkinTime == null) {
      if (other.checkinTime != null)
        return false;
    } else if (!checkinTime.equals(other.checkinTime))
      return false;
    if (id != other.id)
      return false;
    if (name == null) {
      if (other.name != null)
        return false;
    } else if (!name.equals(other.name))
      return false;
    if (portfolioLink == null) {
      if (other.portfolioLink != null)
        return false;
    } else if (!portfolioLink.equals(other.portfolioLink))
      return false;
    return true;
  }
  
  
}
