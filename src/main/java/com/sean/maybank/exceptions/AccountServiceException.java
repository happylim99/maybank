package com.sean.maybank.exceptions;

public class AccountServiceException extends RuntimeException{

	private static final long serialVersionUID = -3036254026611575279L;
	
	public AccountServiceException(String message) {
		super(message);
	}

}
