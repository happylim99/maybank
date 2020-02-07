package com.sean.maybank.exceptions;

public class TransferServiceException extends RuntimeException{

	private static final long serialVersionUID = -3836267009277903919L;
	
	public TransferServiceException(String message) {
		super(message);
	}

}
