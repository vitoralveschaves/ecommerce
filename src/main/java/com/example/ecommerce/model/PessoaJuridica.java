package com.example.ecommerce.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.br.CNPJ;

@Entity
@Table(name = "pessoa_juridica")
@PrimaryKeyJoinColumn(name = "id")
public class PessoaJuridica extends Pessoa{

	private static final long serialVersionUID = 1L;
	
	@NotBlank(message = "Campo obrigatório")
	@CNPJ(message = "CNPJ inválido")
	@Column(nullable = false)
	private String cnpj;
	
	@NotBlank(message = "Campo obrigatório")
	@NotNull(message = "Campo obrigatório")
	@Column(name = "inscricao_estadual", nullable = false)
	private String inscricaoEstadual;
	
	@Column(name = "inscricao_municipal")
	private String inscricaoMunicipal;
	
	@NotBlank(message = "Campo obrigatório")
	@NotNull(message = "Campo obrigatório")
	@Column(name = "nome_fantasia", nullable = false)
	private String nomeFantasia;
	
	@NotBlank(message = "Campo obrigatório")
	@Column(name = "razao_social", nullable = false)
	private String razaoSocial;
	
	private String categoria;

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getInscricaoEstadual() {
		return inscricaoEstadual;
	}

	public void setInscricaoEstadual(String inscricaoEstadual) {
		this.inscricaoEstadual = inscricaoEstadual;
	}

	public String getInscricaoMunicipal() {
		return inscricaoMunicipal;
	}

	public void setInscricaoMunicipal(String inscricaoMunicipal) {
		this.inscricaoMunicipal = inscricaoMunicipal;
	}

	public String getNomeFantasia() {
		return nomeFantasia;
	}

	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
}
