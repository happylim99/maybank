package com.sean.maybank.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sean.maybank.dto.UserDto;
import com.sean.maybank.request.UserRequest;
import com.sean.maybank.response.UserResponse;
import com.sean.maybank.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	UserService userService;
	
	@GetMapping("/hello")
	public String hello() {
		return "hello";
	}
	
	@PostMapping
	public UserResponse createUser(@RequestBody UserRequest users) {
		UserDto userDto = modelMapper.map(users, UserDto.class);
		UserDto createdUser = userService.createUser(userDto);
		UserResponse returnValue = modelMapper.map(createdUser, UserResponse.class);
		return returnValue;
	}

}
