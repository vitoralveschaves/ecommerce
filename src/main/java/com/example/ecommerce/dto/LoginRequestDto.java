package com.example.ecommerce.dto;

import java.io.Serializable;

public class LoginRequestDto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String login;
	
	private String senha;

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
}
