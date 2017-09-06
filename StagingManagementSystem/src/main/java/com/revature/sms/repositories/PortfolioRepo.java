package com.revature.sms.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.sms.entities.PortfolioStatus;

public interface PortfolioRepo extends JpaRepository<PortfolioStatus, Long>{
	public PortfolioStatus findStatusByPortfolioStatusId(Long id);
}
