package com.sean.maybank.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

import org.modelmapper.ModelMapper;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sean.maybank.model.Transfer;

public class TransferDto implements Serializable {

	private static final long serialVersionUID = 4305274104125792171L;

	private ModelMapper modelMapper = new ModelMapper();

	private int id;
	private BigDecimal trxAmount;
	private LocalDate trxDate;
	private LocalTime trxTime;
	private String description;

	@JsonIgnoreProperties("transfers")
	private AccountDto account;

	public TransferDto() {
		super();
	}

	public TransferDto(Transfer transfer) {
		super();
		this.id = transfer.getId();
		this.trxAmount = transfer.getTrxAmount();
		this.trxDate = transfer.getTrxDate();
		this.trxTime = transfer.getTrxTime();
		this.description = transfer.getDescription();
		if (transfer.getAccount() != null) {
			this.account = modelMapper.map(transfer.getAccount(), AccountDto.class);
		}

	}

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

	public AccountDto getAccount() {
		return account;
	}

	public void setAccount(AccountDto account) {
		this.account = account;
	}
	
}
