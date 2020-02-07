package com.sean.maybank.dto;

import java.io.Serializable;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sean.maybank.model.Customer;

public class CustomerDto implements Serializable {

	private static final long serialVersionUID = 7585195612248531848L;

	private ModelMapper modelMapper = new ModelMapper();

	private int id;
	private String name;
	private String email;

	@JsonIgnoreProperties("customer")
	private List<AccountDto> accounts;

	public CustomerDto() {
		super();
	}

	public CustomerDto(Customer customer) {
		super();
		this.id = customer.getId();
		this.name = customer.getName();
		this.email = customer.getEmail();
		if (customer.getAccounts() != null || !customer.getAccounts().isEmpty()) {
			this.accounts = modelMapper.map(customer.getAccounts(), new TypeToken<List<AccountDto>>() {
			}.getType());
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<AccountDto> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<AccountDto> accounts) {
		this.accounts = accounts;
	}

}
