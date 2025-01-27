package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.ecommerce.EcommerceApplication;
import com.example.ecommerce.controller.PessoaController;
import com.example.ecommerce.enums.TipoEndereco;
import com.example.ecommerce.model.Endereco;
import com.example.ecommerce.model.PessoaJuridica;

import junit.framework.TestCase;

@SpringBootTest(classes = EcommerceApplication.class)
public class PessoaUsuarioTest extends TestCase {
	
	@Autowired
	private PessoaController pessoaController;
	
	@Test
	public void testCadastroPessoa() {
		
		PessoaJuridica pessoaJuridica = new PessoaJuridica();
		pessoaJuridica.setCnpj("1234");
		pessoaJuridica.setNome("Usuário Teste");
		pessoaJuridica.setEmail("teste@gmail.com");
		pessoaJuridica.setTelefone("45999795800");
		pessoaJuridica.setInscricaoEstadual("65556565656665");
		pessoaJuridica.setInscricaoMunicipal("55554565656565");
		pessoaJuridica.setNomeFantasia("54556565665");
		pessoaJuridica.setRazaoSocial("4656656566");
		
		Endereco endereco1 = new Endereco();
		endereco1.setBairro("Jardim Dias");
		endereco1.setCep("556556565");
		endereco1.setComplemento("Casa");
		endereco1.setEmpresa(pessoaJuridica);
		endereco1.setNumero("123");
		endereco1.setPessoa(pessoaJuridica);
		endereco1.setRuaLogradouro("Av. são joao");
		endereco1.setTipoEndereco(TipoEndereco.COBRANCA);
		endereco1.setUf("PR");
		endereco1.setCidade("Curitiba");
		
		Endereco endereco2 = new Endereco();
		endereco2.setBairro("Jardim Maracana");
		endereco2.setCep("7878778");
		endereco2.setComplemento("Casa");
		endereco2.setEmpresa(pessoaJuridica);
		endereco2.setNumero("321");
		endereco2.setPessoa(pessoaJuridica);
		endereco2.setRuaLogradouro("Av. maringá");
		endereco2.setTipoEndereco(TipoEndereco.ENTREGA);
		endereco2.setUf("PR");
		endereco2.setCidade("Curitiba");
		
		pessoaJuridica.getEnderecos().add(endereco2);
		pessoaJuridica.getEnderecos().add(endereco1);
		
		pessoaController.salvarPj(pessoaJuridica);
	}
}
