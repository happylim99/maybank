package com.sean.maybank.service;

import com.sean.maybank.dto.AccountDto;
import com.sean.maybank.model.Account;

public interface AccountService {
	
	AccountDto createAccount(AccountDto accountDto);
	
	Account getAccountByAccountNumber(String accountNumber);

}
