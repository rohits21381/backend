package com.skyrics.app.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skyrics.app.entities.User;


public interface UserRepository extends JpaRepository<User,Integer>{
	
	Optional<User> findByEmail(String email);
}
