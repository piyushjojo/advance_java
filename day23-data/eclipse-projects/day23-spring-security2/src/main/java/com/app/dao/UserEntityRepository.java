package com.app.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.entities.Role;
import com.app.entities.UserEntity;

public interface UserEntityRepository extends JpaRepository<UserEntity, Long> {
//add a custom query method get user details by email along with the role
	@Query("select u from UserEntity u join fetch u.role where u.email=?1")
	Optional<UserEntity> findByEmail(String email);
}
