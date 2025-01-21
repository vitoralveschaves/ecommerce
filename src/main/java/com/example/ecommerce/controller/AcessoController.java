package com.example.ecommerce.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.ecommerce.model.Acesso;
import com.example.ecommerce.repository.AcessoRepository;
import com.example.ecommerce.service.AcessoService;

@RestController
public class AcessoController {
	
	private final AcessoService acessoService;
	private final AcessoRepository acessoRepository;
	
	public AcessoController(AcessoService acessoService, AcessoRepository acessoRepository) {
		this.acessoService = acessoService;
		this.acessoRepository = acessoRepository;
	}

	@PostMapping(value = "**/salvarAcesso")
	public ResponseEntity<Acesso> salvarAcesso(@RequestBody Acesso acesso) {
		Acesso acessoSalvo = acessoService.save(acesso);
		return new ResponseEntity<Acesso>(acessoSalvo, HttpStatus.OK);
	}
	
	@DeleteMapping(value = "**/deletarAcesso")
	public ResponseEntity<Object> deletarAcesso(@RequestBody Acesso acesso) {
		acessoRepository.delete(acesso);
		return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping(value = "**/obterAcesso/{id}")
	public ResponseEntity<Acesso> obterAcesso(@PathVariable("id") Long id) {
		Acesso acesso = acessoRepository.findById(id).get();
		return new ResponseEntity<Acesso>(acesso, HttpStatus.OK);
	}
}
