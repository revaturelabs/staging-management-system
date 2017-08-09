package com.revature.repositories;

import com.revature.entities.Marketer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MarketerRepo extends JpaRepository<Marketer, Long> {

}
