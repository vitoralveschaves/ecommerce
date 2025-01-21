package com.example.ecommerce.dto;

import java.io.Serializable;

public class ObjetoErroDto implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String error;
	private String code;
	
	public ObjetoErroDto() {
	}
	
	public ObjetoErroDto(String error, String code) {
		this.error = error;
		this.code = code;
	}
	
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
}
