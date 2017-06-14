package com.revature.repositories;

import com.revature.entities.ClientQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientQRepo extends JpaRepository<ClientQuestion, Long> {

}
