package com.revature.sms.repositories;

import com.revature.sms.entities.AssociatesStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssociateStatusRepo extends JpaRepository<AssociatesStatus, Long> {
    }
