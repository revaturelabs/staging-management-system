package com.revature.sms.services;

import java.util.*;

import org.springframework.stereotype.Service;

import com.revature.sms.entities.Associate;

@Service
public class TotalReport {

  public class Tuple<I, T> {
    private I first;
    private T second;

    public Tuple(I first, T second) {
      super();
      this.first = first;
      this.second = second;
    }

    public Tuple() {
      super();
      // TODO Auto-generated constructor stub
    }

    public I getFirst() {
      return first;
    }

    public void setFirst(I first) {
      this.first = first;
    }

    public T getSecond() {
      return second;
    }

    public void setSecond(T second) {
      this.second = second;
    }

    @Override
    public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + getOuterType().hashCode();
      result = prime * result + ((first == null) ? 0 : first.hashCode());
      result = prime * result + ((second == null) ? 0 : second.hashCode());
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
      Tuple other = (Tuple) obj;
      if (!getOuterType().equals(other.getOuterType()))
        return false;
      if (first == null) {
        if (other.first != null)
          return false;
      } else if (!first.equals(other.first))
        return false;
      if (second == null) {
        if (other.second != null)
          return false;
      } else if (!second.equals(other.second))
        return false;
      return true;
    }

    private TotalReport getOuterType() {
      return TotalReport.this;
    }

    @Override
    public String toString() {
      return "Tuple [first=" + first + ", second=" + second + "]";
    }

  }

  public class TotalData {
    private String batchName;
    private long totalAvailable;
    private long totalUnavailable;
    private List<Tuple<String, String>> availibleAssociates = new ArrayList<>();
    private List<Tuple<String, String>> mappedAssociates = new ArrayList<>();


    public List<Tuple<String, String>> getAvailible() {
      return availibleAssociates;
    }
    
    public List<Tuple<String, String>> getMapped() {
      return mappedAssociates;
    }

    public void setAvailible(List<Tuple<String, String>> associates) {
      this.availibleAssociates = associates;
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

    public void addAvailible(String name, String portLink) {
      availibleAssociates.add(new Tuple<String, String>(name, portLink));
    }
    
    public void addMapped(String name, String portLink) {
      mappedAssociates.add(new Tuple<String, String>(name, portLink));
    }
  }

  private Map<String, TotalData> totaldata = new HashMap<String, TotalData>();

  public void addBatch(Associate associate) {
    String batchName = associate.getBatch().getBatchType().getValue();
    String name = associate.getName();
    String portLink = associate.getPortfolioLink();

    TotalData t = totaldata.get(batchName);
    if (t == null) {
      totaldata.put(batchName, t = new TotalData(batchName));
    }
    if (associate.getLockedTo() != null) {
    }
    
    boolean unAvialable = associate.isActive() && associate.getLockedTo() != null
        && associate.getLockedTo().isPriority();

    boolean totalAvailable = associate.isActive();
    if (totalAvailable) {
      t.totalAvailable++;
      if (unAvialable) {
        t.totalUnavailable++;
        t.addMapped(name, portLink);
      } else {
        t.addAvailible(name, portLink);
      }
    }
  }

  public Collection<TotalData> returnTotalData() {
    return totaldata.values();
  }

  public Map<String, TotalData> getTotaldata() {
    return totaldata;
  }

  public void setTotaldata(Map<String, TotalData> totaldata) {
    this.totaldata = totaldata;
  }

  

  public Collection<TotalData> process(Set<Associate> associates) {
    this.totaldata = new HashMap<>();
    for (Associate a : associates) {
      this.addBatch(a);
    }
    return returnTotalData();
  }
}
