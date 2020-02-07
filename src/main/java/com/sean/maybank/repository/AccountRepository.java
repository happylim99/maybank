package com.sean.maybank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sean.maybank.model.Account;

public interface AccountRepository extends JpaRepository<Account, Integer>{
	
	Account findByAccountNumber(String accountNumber);
	
	Account findAccountIdByAccountNumber(String accountNumber);
}
