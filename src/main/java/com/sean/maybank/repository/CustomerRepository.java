package com.sean.maybank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sean.maybank.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer>{
	
	Customer findById(int id);

}
