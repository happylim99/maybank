package com.sean.maybank.exceptions;

public class UserServiceException extends RuntimeException {

	private static final long serialVersionUID = 4580468877784951996L;

	public UserServiceException(String message) {
		super(message);
	}

}
