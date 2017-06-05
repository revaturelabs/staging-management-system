package com.revature.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.entities.Permission;

public interface PermissionRepo extends JpaRepository<Permission, Long> {

	Permission findByLevel(String level);
}
