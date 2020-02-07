package com.sean.maybank.exceptions;

public class CustomerServiceException extends RuntimeException{

	private static final long serialVersionUID = -232051628368132333L;
	
	public CustomerServiceException(String message) {
		super(message);
	}

}
