package com.sean.maybank.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sean.maybank.dto.AccountDto;
import com.sean.maybank.exceptions.AccountServiceException;
import com.sean.maybank.exceptions.message.ErrorMessages;
import com.sean.maybank.model.Account;
import com.sean.maybank.repository.AccountRepository;
import com.sean.maybank.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {
	
	@Autowired
	AccountRepository accountRepository;
	
	@Autowired
	ModelMapper modelMapper;

	@Override
	public AccountDto createAccount(AccountDto accountDto) {
		if (accountRepository.findByAccountNumber(accountDto.getAccountNumber()) != null)
			throw new AccountServiceException(ErrorMessages.RECORD_ALREADY_EXISTS.getErrorMessage());

		Account account = modelMapper.map(accountDto, Account.class);

		Account storedDetails = accountRepository.save(account);
		
		AccountDto returnValue = modelMapper.map(storedDetails, AccountDto.class);

		return returnValue;
	}

	@Override
	public Account getAccountByAccountNumber(String accountNumber) {
		Account account = accountRepository.findAccountIdByAccountNumber(accountNumber);
		return account;
	}

}
