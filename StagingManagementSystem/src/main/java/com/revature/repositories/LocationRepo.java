package com.revature.repositories;

import com.revature.entities.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepo extends JpaRepository<Location, Long> {

    Location findByName(String name);
}
