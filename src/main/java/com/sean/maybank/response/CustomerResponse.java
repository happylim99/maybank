package com.sean.maybank.response;

import com.sean.maybank.dto.CustomerDto;

public class CustomerResponse {

	private int id;
	private String name;
	private String email;

	public CustomerResponse() {
		super();
	}

	public CustomerResponse(CustomerDto customerDto) {
		super();
		this.id = customerDto.getId();
		this.name = customerDto.getName();
		this.email = customerDto.getEmail();
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
	
}
