package com.sean.maybank.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.sean.maybank.model.MyUser;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class UserDto implements Serializable {

	private static final long serialVersionUID = -8255103613603583239L;

	private int id;
	private String email;
	private String password;
	private String encryptedPassword;

	public UserDto() {
		super();
	}

	public UserDto(MyUser myUser) {
		super();
		this.id = myUser.getId();
		this.email = myUser.getEmail();
		this.encryptedPassword = myUser.getEncryptedPassword();
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEncryptedPassword() {
		return encryptedPassword;
	}

	public void setEncryptedPassword(String encryptedPassword) {
		this.encryptedPassword = encryptedPassword;
	}

}
