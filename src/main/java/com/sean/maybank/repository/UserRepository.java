package com.sean.maybank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sean.maybank.model.MyUser;

public interface UserRepository extends JpaRepository<MyUser, Integer>{
	
	MyUser findByEmail(String email);

}
