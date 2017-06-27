package com.revature.repositories;


import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.revature.entities.StaggingAssociate;

public interface StaggingAssociateRepo extends JpaRepository<StaggingAssociate, Long> {

  @Query(nativeQuery = true, value = "SELECT * FROM TABLE(GET_STAGGING_ASSOC_BY_DATE(:date))")
  Set<StaggingAssociate> getAssociatesInStaggingOn(@Param(value = "date") String date);
}
