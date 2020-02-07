package com.sean.maybank.response;

import com.sean.maybank.dto.UserDto;

public class UserResponse {

	private int id;
	private String email;

	public UserResponse() {
		super();
	}

	public UserResponse(UserDto userDto) {
		super();
		this.id = userDto.getId();
		this.email = userDto.getEmail();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
