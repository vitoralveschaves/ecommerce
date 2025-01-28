package com.example.ecommerce.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ecommerce.exception.CustomException;
import com.example.ecommerce.model.PessoaJuridica;
import com.example.ecommerce.repository.PessoaRepository;
import com.example.ecommerce.service.PessoaService;

@RestController
@RequestMapping(value = "/pessoa")
public class PessoaController {
	
	private final PessoaRepository pessoaRepository;
	private final PessoaService pessoaService;
	
	public PessoaController(PessoaRepository pessoaRepository, PessoaService pessoaService) {
		this.pessoaRepository = pessoaRepository;
		this.pessoaService = pessoaService;
	}

	@PostMapping(value = "/juridica")
	public ResponseEntity<PessoaJuridica> salvarPj(@RequestBody PessoaJuridica pessoaJuridica) {
		
		if(pessoaJuridica == null) {
			throw new CustomException("Pessoa juridica não pode ser null");
		}
		
		if(pessoaJuridica.getId() == null && pessoaRepository.existeCnpjCadastrado(pessoaJuridica.getCnpj()) != null) {
			throw new CustomException("Já existe CNPJ cadastrado com número: " + pessoaJuridica.getCnpj());
		}
		
		if(pessoaJuridica.getId() == null && pessoaRepository.existeInscricaoEstadualCadastrado(pessoaJuridica.getInscricaoEstadual()) != null) {
			throw new CustomException("Já existe Inscrição estadual cadastrada com número: " + pessoaJuridica.getInscricaoEstadual());
		}
		
		pessoaJuridica = pessoaService.salvarPj(pessoaJuridica);
		return new ResponseEntity<PessoaJuridica>(pessoaJuridica, HttpStatus.OK);
	}
}
