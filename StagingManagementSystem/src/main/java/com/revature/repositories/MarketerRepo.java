package com.revature.repositories;

import com.revature.entities.Marketer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface MarketerRepo extends JpaRepository<Marketer, Long> {
    Set<Marketer> getAll();
}
