package com.example.ecommerce.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.ecommerce.model.PessoaJuridica;

@Repository
public interface PessoaRepository extends CrudRepository<PessoaJuridica, Long> {
	
	@Query(value = "select pj from PessoaJuridica pj where pj.cnpj = ?1")
	PessoaJuridica existeCnpjCadastrado(String cnpj);
}
