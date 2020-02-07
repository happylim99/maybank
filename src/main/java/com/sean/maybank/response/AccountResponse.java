package com.sean.maybank.response;

import com.sean.maybank.dto.AccountDto;

public class AccountResponse {

	private int id;
	private String accountNumber;

	public AccountResponse() {
		super();
	}

	public AccountResponse(AccountDto accountDto) {
		super();
		this.id = accountDto.getId();
		this.accountNumber = accountDto.getAccountNumber();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	
}
