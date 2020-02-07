package com.sean.maybank.service.impl;

import java.util.ArrayList;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.sean.maybank.dto.UserDto;
import com.sean.maybank.exceptions.UserServiceException;
import com.sean.maybank.exceptions.message.ErrorMessages;
import com.sean.maybank.model.MyUser;
import com.sean.maybank.repository.UserRepository;
import com.sean.maybank.service.UserService;
import com.sean.maybank.utils.MyUtils;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	MyUtils myUtils;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		MyUser myUser = userRepository.findByEmail(email);
		if (myUser == null) {
			throw new UsernameNotFoundException("User not found");
		}

		return new User(myUser.getEmail(), myUser.getEncryptedPassword(), new ArrayList<>());
	}

	@Override
	public UserDto createUser(UserDto userDto) {
		if (userRepository.findByEmail(userDto.getEmail()) != null)
			throw new UserServiceException(ErrorMessages.RECORD_ALREADY_EXISTS.getErrorMessage());

		MyUser myUser = modelMapper.map(userDto, MyUser.class);

		myUser.setEncryptedPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
		MyUser storedUserDetails = userRepository.save(myUser);
		
		UserDto returnValue = modelMapper.map(storedUserDetails, UserDto.class);

		return returnValue;
	}

	@Override
	public UserDto getUser(String email) {
		// TODO Auto-generated method stub
		return null;
	}

}
