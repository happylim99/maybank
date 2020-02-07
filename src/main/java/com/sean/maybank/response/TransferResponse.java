package com.sean.maybank.response;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

import org.modelmapper.ModelMapper;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.sean.maybank.dto.TransferDto;

@JsonFilter("TransferFilter")
public class TransferResponse {
	
	private ModelMapper modelMapper = new ModelMapper();

	private int id;
	private BigDecimal trxAmount;
	private LocalDate trxDate;
	private LocalTime trxTime;
	private String description;
	private AccountResponse account;
	private CustomerResponse customer;

	public TransferResponse() {
		super();
	}

	public TransferResponse(TransferDto transferDto) {
		super();
		this.id = transferDto.getId();
		this.trxAmount = transferDto.getTrxAmount();
		this.trxDate = transferDto.getTrxDate();
		this.trxTime = transferDto.getTrxTime();
		this.description = transferDto.getDescription();
		if (transferDto.getAccount() != null) {
			this.account = modelMapper.map(transferDto.getAccount(), AccountResponse.class);
		}
		if (transferDto.getAccount().getCustomer() != null) {
			this.customer = modelMapper.map(transferDto.getAccount().getCustomer(), CustomerResponse.class);
		}
	}
	/*
	public TransferResponse(TransferDto transferDto, Object[] paginationLinks) {
		super();
		this.id = transferDto.getId();
		this.trxAmount = transferDto.getTrxAmount();
		this.trxDate = transferDto.getTrxDate();
		this.trxTime = transferDto.getTrxTime();
		this.description = transferDto.getDescription();
		if (transferDto.getAccount() != null) {
			this.account = modelMapper.map(transferDto.getAccount(), AccountResponse.class);
		}
		
	}
	*/
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public BigDecimal getTrxAmount() {
		return trxAmount;
	}

	public void setTrxAmount(BigDecimal trxAmount) {
		this.trxAmount = trxAmount;
	}

	public LocalDate getTrxDate() {
		return trxDate;
	}

	public void setTrxDate(LocalDate trxDate) {
		this.trxDate = trxDate;
	}

	public LocalTime getTrxTime() {
		return trxTime;
	}

	public void setTrxTime(LocalTime trxTime) {
		this.trxTime = trxTime;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public AccountResponse getAccount() {
		return account;
	}

	public void setAccount(AccountResponse account) {
		this.account = account;
	}
	
	public CustomerResponse getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerResponse customer) {
		this.customer = customer;
	}
	
}
