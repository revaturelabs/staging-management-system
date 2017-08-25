package com.revature.sms.repositories;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.sms.entities.Associate;
import com.revature.sms.entities.Certifications;

public interface CertificationsRepo extends JpaRepository<Certifications, Long>{
  Set<Certifications> getByAssociate_Id(Long id);
 
  //List<Certifications> getByCert_type();
}
