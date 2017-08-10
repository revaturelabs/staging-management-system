package com.revature.sms.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.sms.entities.Permission;

public interface PermissionRepo extends JpaRepository<Permission, Long> {

    Permission findByLevel(String level);
}
