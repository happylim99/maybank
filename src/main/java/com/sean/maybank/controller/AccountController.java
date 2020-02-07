package com.sean.maybank.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sean.maybank.dto.AccountDto;
import com.sean.maybank.model.Account;
import com.sean.maybank.repository.AccountRepository;
import com.sean.maybank.request.AccountRequest;
import com.sean.maybank.response.AccountResponse;
import com.sean.maybank.service.AccountService;

@RestController
@RequestMapping("/accounts")
public class AccountController {
	
	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	AccountService accountService;
	
	@Autowired
	AccountRepository accountRepository;
	
	@PostMapping
	public AccountResponse createUser(@RequestBody AccountRequest accountRequest) {
		AccountDto accountDto = modelMapper.map(accountRequest, AccountDto.class);
		//AccountDto accountDto = new AccountDto(accountRequest);
		AccountDto createdDetails = accountService.createAccount(accountDto);
		AccountResponse returnValue = modelMapper.map(createdDetails, AccountResponse.class);
		return returnValue;
	}
	
	@GetMapping("/all")
	public List<Account> getAccounts() {
		List<Account> account = accountRepository.findAll();
		return account;
	}

}
