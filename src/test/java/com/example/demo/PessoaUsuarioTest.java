package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.ecommerce.EcommerceApplication;
import com.example.ecommerce.controller.PessoaController;
import com.example.ecommerce.model.PessoaJuridica;

import junit.framework.TestCase;

@SpringBootTest(classes = EcommerceApplication.class)
public class PessoaUsuarioTest extends TestCase {
	
	@Autowired
	private PessoaController pessoaController;
	
	@Test
	public void testCadastroPessoa() {
		
		PessoaJuridica pessoaJuridica = new PessoaJuridica();
		pessoaJuridica.setCnpj("123456");
		pessoaJuridica.setNome("Alex fernando");
		pessoaJuridica.setEmail("alex.fernando.egidio@gmail.com");
		pessoaJuridica.setTelefone("45999795800");
		pessoaJuridica.setInscricaoEstadual("65556565656665");
		pessoaJuridica.setInscricaoMunicipal("55554565656565");
		pessoaJuridica.setNomeFantasia("54556565665");
		pessoaJuridica.setRazaoSocial("4656656566");
		
		pessoaController.salvarPj(pessoaJuridica);
	}
}
