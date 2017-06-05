package com.revature.repositories;

import com.revature.entities.BatchType;
import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.entities.Batch;

import java.time.LocalDate;
import java.util.Set;

public interface BatchRepo extends JpaRepository<Batch, Long> {
    Batch getByStartDate(LocalDate startDate);
    Set<Batch> getAllByBatchType(BatchType batchType);
    
}
