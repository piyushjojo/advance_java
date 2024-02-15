package com.app.dao;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.Role;
import com.app.entities.UserRole;

public interface RoleRepository extends JpaRepository<Role, Long> {
	//Optional<Role> findByRoleName(UserRole role);
	Set<Role> findByRoleNameIn(Set<UserRole> roles);
}
