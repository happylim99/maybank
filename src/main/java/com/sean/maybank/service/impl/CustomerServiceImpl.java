package com.sean.maybank.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sean.maybank.dto.CustomerDto;
import com.sean.maybank.exceptions.CustomerServiceException;
import com.sean.maybank.exceptions.message.ErrorMessages;
import com.sean.maybank.model.Customer;
import com.sean.maybank.repository.CustomerRepository;
import com.sean.maybank.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	ModelMapper modelMapper;

	@Override
	public CustomerDto createCustomer(CustomerDto customerDto) {
		
		if (customerRepository.findById(customerDto.getId()) != null)
			throw new CustomerServiceException(ErrorMessages.RECORD_ALREADY_EXISTS.getErrorMessage());

		Customer customer = modelMapper.map(customerDto, Customer.class);

		Customer storedDetails = customerRepository.save(customer);
		
		CustomerDto returnValue = modelMapper.map(storedDetails, CustomerDto.class);

		return returnValue;
	}

}
