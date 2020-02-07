package com.sean.maybank.request;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

public class TransferRequest {

	private int id;
	private BigDecimal trxAmount;
	private LocalDate trxDate;
	private LocalTime trxTime;
	private String description;
	private int accountId;

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

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

}
