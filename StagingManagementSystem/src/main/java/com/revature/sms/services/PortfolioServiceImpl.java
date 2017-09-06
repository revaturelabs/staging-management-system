package com.revature.sms.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.sms.entities.PortfolioStatus;
import com.revature.sms.repositories.PortfolioRepo;
@Service
public class PortfolioServiceImpl implements PortfolioService {
 @Autowired
 PortfolioRepo portRepo;
	@Override
	public PortfolioStatus findStatusById(long id) 
	{
		// TODO Auto-generated method stub
		return portRepo.findStatusByPortfolioStatusId(id);
	//return null;
	}
	public PortfolioStatus save(PortfolioStatus file)
	{
		portRepo.save(file);
		return file;
	}
	public void delete(long id)
	{
		portRepo.delete(id);
	
	}
	
}
