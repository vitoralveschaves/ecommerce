package com.example.ecommerce.dto;

import java.io.Serializable;

public class LoginResponseDto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String token;

	public LoginResponseDto(String token) {
		this.token = token;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
