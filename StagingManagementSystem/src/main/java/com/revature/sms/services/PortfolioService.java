package com.revature.sms.services;
import java.util.List;

import com.revature.sms.entities.PortfolioStatus;
public interface PortfolioService 
{
       public PortfolioStatus findStatusById(long id);
       public PortfolioStatus save(PortfolioStatus file);
       public void delete(long id);
}
