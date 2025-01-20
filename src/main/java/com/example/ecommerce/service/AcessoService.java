package com.example.ecommerce.service;

import org.springframework.stereotype.Service;

import com.example.ecommerce.model.Acesso;
import com.example.ecommerce.repository.AcessoRepository;

@Service
public class AcessoService {
	
	private final AcessoRepository acessoRepository;
	
	public AcessoService(AcessoRepository acessoRepository) {
		this.acessoRepository = acessoRepository;
	}

	public Acesso save(Acesso acesso) {
		return acessoRepository.save(acesso);
	}
}
