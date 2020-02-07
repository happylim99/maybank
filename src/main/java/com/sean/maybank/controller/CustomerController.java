package com.sean.maybank.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sean.maybank.dto.CustomerDto;
import com.sean.maybank.request.CustomerRequest;
import com.sean.maybank.response.CustomerResponse;
import com.sean.maybank.service.CustomerService;

@RestController
@RequestMapping("/customers")
public class CustomerController {
	
	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	CustomerService customerService;
	
	@GetMapping("/hello")
	public String hello() {
		return "hello";
	}
	
	@PostMapping
	public CustomerResponse createUser(@RequestBody CustomerRequest customerRequest) {
		CustomerDto customerDto = modelMapper.map(customerRequest, CustomerDto.class);
		CustomerDto createdDetails = customerService.createCustomer(customerDto);
		CustomerResponse returnValue = modelMapper.map(createdDetails, CustomerResponse.class);
		return returnValue;
	}

}
