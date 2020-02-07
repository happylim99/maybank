package com.sean.maybank.dto;

import java.io.Serializable;
import java.util.List;

import org.modelmapper.ModelMapper;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import com.sean.maybank.model.Account;

public class AccountDto implements Serializable {

	private static final long serialVersionUID = 6731727792915452573L;

	private ModelMapper modelMapper = new ModelMapper();

	private int id;
	private String accountNumber;

	@JsonIgnoreProperties("accounts")
	private CustomerDto customer;

	@JsonIgnoreProperties("account")
	private List<TransferDto> transfers;

	public AccountDto() {
		super();
	}

	public AccountDto(Account account) {
		super();
		this.id = account.getId();
		this.accountNumber = account.getAccountNumber();
		if (account.getCustomer() != null) {
			this.customer = modelMapper.map(account.getCustomer(), CustomerDto.class);
		}

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
		System.out.println();
		this.accountNumber = accountNumber;
	}

	public CustomerDto getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerDto customer) {
		this.customer = customer;
	}

	public List<TransferDto> getTransfers() {
		return transfers;
	}

	public void setTransfers(List<TransferDto> transfers) {
		this.transfers = transfers;
	}

}
