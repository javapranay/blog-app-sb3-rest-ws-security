package com.smedia.blogsapp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smedia.blogsapp.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{
	
	Optional<User> findByEmail(String email);
	
	Optional<User> findByUsername(String username);
	
	Optional<User> findByUsernameOrEmail(String username, String password);
	
	Boolean existsByUsername(String username);
	
	Boolean existsByEmail(String email);
}
