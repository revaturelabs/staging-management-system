package com.revature.sms.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.sms.entities.Batch;
import com.revature.sms.entities.BatchType;

import java.time.LocalDate;
import java.util.Set;

public interface BatchRepo extends JpaRepository<Batch, Long> {
	Batch getBySalesforceId(String salesforceId);
    Batch getByStartDate(LocalDate startDate);
    Set<Batch> getAllByBatchType(BatchType batchType);
}
