package com.skyrics.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skyrics.app.entities.Role;

public interface RoleRepository extends JpaRepository<Role,Integer>{
	
}
