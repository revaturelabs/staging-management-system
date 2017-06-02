package com.revature.repositories;

import com.revature.entities.Manager;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Mykola Nikitin on 6/2/17.
 */
public interface ManagerRepo extends JpaRepository<Manager, Long>{
    Manager getByCredential_Username(String username);
}
