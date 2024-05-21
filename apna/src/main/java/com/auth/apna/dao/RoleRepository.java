package com.auth.apna.dao;

import com.auth.apna.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<UserRole, Long> {

    public UserRole findUserRoleByRole(String role);
}
