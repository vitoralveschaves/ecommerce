package com.example.ecommerce.exception;

public class TokenInvalidoException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public TokenInvalidoException(String message) {
		super(message);
	}
}
