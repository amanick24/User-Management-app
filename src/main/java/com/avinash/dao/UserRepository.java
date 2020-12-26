package com.avinash.dao;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.avinash.entity.UserEntity;


public interface UserRepository extends JpaRepository<UserEntity, Serializable> {
	
	public UserEntity findByEmailId(String emailId);
	
	public UserEntity findByEmailIdAndPassword(String emailId, String password);

}
