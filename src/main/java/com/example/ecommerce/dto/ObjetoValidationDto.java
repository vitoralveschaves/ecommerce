package com.example.ecommerce.dto;

import java.io.Serializable;

public class ObjetoValidationDto implements Serializable{

	private static final long serialVersionUID = 1L;
	
	public String campo;
	
	public String mesagem;

	public ObjetoValidationDto(String campo, String mesagem) {
		super();
		this.campo = campo;
		this.mesagem = mesagem;
	}

	public String getCampo() {
		return campo;
	}

	public void setCampo(String campo) {
		this.campo = campo;
	}

	public String getMesagem() {
		return mesagem;
	}

	public void setMesagem(String mesagem) {
		this.mesagem = mesagem;
	}
}
