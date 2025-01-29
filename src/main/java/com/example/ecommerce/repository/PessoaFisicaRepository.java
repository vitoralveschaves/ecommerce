package com.example.ecommerce.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.ecommerce.model.PessoaFisica;

@Repository
public interface PessoaFisicaRepository extends CrudRepository<PessoaFisica, Long>{
	
	@Query(value = "select pf from PessoaFisica pf where pf.cpf = ?1")
	PessoaFisica existeCpfCadastrado(String cpf);
}
