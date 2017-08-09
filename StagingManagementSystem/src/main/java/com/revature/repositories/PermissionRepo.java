package com.revature.repositories;

import com.revature.entities.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepo extends JpaRepository<Permission, Long> {

    Permission findByLevel(String level);
}
