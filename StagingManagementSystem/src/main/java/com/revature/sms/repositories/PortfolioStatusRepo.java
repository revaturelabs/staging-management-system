package com.revature.sms.repositories;

import com.revature.sms.entities.PortfolioStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PortfolioStatusRepo  extends JpaRepository<PortfolioStatus, Long> {
}
