package com.example.ecommerce.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ecommerce.exception.CustomException;
import com.example.ecommerce.model.PessoaFisica;
import com.example.ecommerce.model.PessoaJuridica;
import com.example.ecommerce.repository.PessoaFisicaRepository;
import com.example.ecommerce.repository.PessoaJuridicaRepository;
import com.example.ecommerce.service.PessoaService;
import com.example.ecommerce.util.ValidarCNPJ;
import com.example.ecommerce.util.ValidarCPF;

@RestController
@RequestMapping
public class PessoaController {
	
	private final PessoaJuridicaRepository pessoaJuridicaRepository;
	private final PessoaFisicaRepository pessoaFisicaRepository;
	private final PessoaService pessoaService;
	
	public PessoaController(PessoaJuridicaRepository pessoaJuridicaRepository, PessoaService pessoaService, PessoaFisicaRepository pessoaFisicaRepository) {
		this.pessoaJuridicaRepository = pessoaJuridicaRepository;
		this.pessoaFisicaRepository = pessoaFisicaRepository;
		this.pessoaService = pessoaService;
	}

	@PostMapping(value = "/pessoa-juridica")
	public ResponseEntity<PessoaJuridica> salvarPj(@RequestBody PessoaJuridica pessoaJuridica) {
		
		if(pessoaJuridica == null) {
			throw new CustomException("Pessoa juridica não pode ser null");
		}
		
		if(pessoaJuridica.getId() == null && pessoaJuridicaRepository.existeCnpjCadastrado(pessoaJuridica.getCnpj()) != null) {
			throw new CustomException("CNPJ inválido ou já cadastrado");
		}
		
		if(pessoaJuridica.getId() == null && pessoaJuridicaRepository.existeInscricaoEstadualCadastrado(pessoaJuridica.getInscricaoEstadual()) != null) {
			throw new CustomException("Inscrição Estadual inválida ou já cadastrada");
		}
		
		if(!ValidarCNPJ.isCNPJ(pessoaJuridica.getCnpj())) {
			throw new CustomException("CNPJ inválido ou já cadastrado");
		}
		
		pessoaJuridica = pessoaService.salvarPj(pessoaJuridica);
		return new ResponseEntity<PessoaJuridica>(pessoaJuridica, HttpStatus.OK);
	}
	
	@PostMapping(value = "/pessoa-fisica")
	public ResponseEntity<PessoaFisica> salvarPf(@RequestBody PessoaFisica pessoaFisica) {
		
		if(pessoaFisica == null) {
			throw new CustomException("Pessoa Física não pode ser null");
		}
		
		if(pessoaFisica.getId() == null && pessoaFisicaRepository.existeCpfCadastrado(pessoaFisica.getCpf()) != null) {
			throw new CustomException("CPF inválido ou já cadastrado");
		}
		
		if(!ValidarCPF.isCPF(pessoaFisica.getCpf())) {
			throw new CustomException("CPF inválido ou já cadastrado");
		}
		
		pessoaFisica = pessoaService.salvarPf(pessoaFisica);
		return new ResponseEntity<PessoaFisica>(pessoaFisica, HttpStatus.OK);
	}
}
