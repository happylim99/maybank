package com.sean.maybank.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.sean.maybank.dto.UserDto;

public interface UserService extends UserDetailsService{
	
	UserDto createUser(UserDto userDto);
	UserDto getUser(String email);

}
