package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.ecommerce.EcommerceApplication;
import com.example.ecommerce.controller.PessoaController;
import com.example.ecommerce.enums.TipoEndereco;
import com.example.ecommerce.model.Endereco;
import com.example.ecommerce.model.PessoaFisica;
import com.example.ecommerce.model.PessoaJuridica;
import com.example.ecommerce.repository.PessoaJuridicaRepository;

import junit.framework.TestCase;

@SpringBootTest(classes = EcommerceApplication.class)
public class PessoaUsuarioTest extends TestCase {
	
	@Autowired
	private PessoaController pessoaController;
	
	@Autowired
	private PessoaJuridicaRepository pessoaJuridicaRepository;
	
	@Test
	public void testCadastroPessoa() {
		
//		PessoaJuridica pessoaJuridica = new PessoaJuridica();
//		pessoaJuridica.setCnpj("15.842.073/0001-06");
//		pessoaJuridica.setNome("John");
//		pessoaJuridica.setEmail("email@example.com");
//		pessoaJuridica.setTelefone("45999795800");
//		pessoaJuridica.setInscricaoEstadual("123456");
//		pessoaJuridica.setInscricaoMunicipal("123456");
//		pessoaJuridica.setNomeFantasia("123456");
//		pessoaJuridica.setRazaoSocial("123456");
//		
//		Endereco endereco1 = new Endereco();
//		endereco1.setBairro("Jardim Dias");
//		endereco1.setCep("556556565");
//		endereco1.setComplemento("Casa");
//		endereco1.setEmpresa(pessoaJuridica);
//		endereco1.setNumero("123");
//		endereco1.setPessoa(pessoaJuridica);
//		endereco1.setRuaLogradouro("Av. são joao");
//		endereco1.setTipoEndereco(TipoEndereco.COBRANCA);
//		endereco1.setUf("PR");
//		endereco1.setCidade("Curitiba");
//		
//		Endereco endereco2 = new Endereco();
//		endereco2.setBairro("Jardim Maracana");
//		endereco2.setCep("7878778");
//		endereco2.setComplemento("Casa");
//		endereco2.setEmpresa(pessoaJuridica);
//		endereco2.setNumero("321");
//		endereco2.setPessoa(pessoaJuridica);
//		endereco2.setRuaLogradouro("Av. maringá");
//		endereco2.setTipoEndereco(TipoEndereco.ENTREGA);
//		endereco2.setUf("PR");
//		endereco2.setCidade("Curitiba");
//		
//		pessoaJuridica.getEnderecos().add(endereco2);
//		pessoaJuridica.getEnderecos().add(endereco1);
//		
//		pessoaController.salvarPj(pessoaJuridica);
		
		
		PessoaJuridica pessoaJuridica = pessoaJuridicaRepository.findById(15l).get();
		
		
		PessoaFisica pessoaFisica = new PessoaFisica();
		pessoaFisica.setCpf("713.482.980-49");
		pessoaFisica.setNome("Alex fernando");
		pessoaFisica.setEmail("alex.fe85549989r9559nando.egidio@gmail.com");
		pessoaFisica.setTelefone("45999795800");
		pessoaFisica.setEmpresa(pessoaJuridica);
		
		Endereco endereco1 = new Endereco();
		endereco1.setBairro("Jd Dias");
		endereco1.setCep("556556565");
		endereco1.setComplemento("Casa cinza");
		endereco1.setNumero("389");
		endereco1.setPessoa(pessoaFisica);
		endereco1.setRuaLogradouro("Av. são joao sexto");
		endereco1.setTipoEndereco(TipoEndereco.COBRANCA);
		endereco1.setUf("PR");
		endereco1.setCidade("Curitiba");
		endereco1.setEmpresa(pessoaJuridica);
		
		
		Endereco endereco2 = new Endereco();
		endereco2.setBairro("Jd Maracana");
		endereco2.setCep("7878778");
		endereco2.setComplemento("Andar 4");
		endereco2.setNumero("555");
		endereco2.setPessoa(pessoaFisica);
		endereco2.setRuaLogradouro("Av. maringá");
		endereco2.setTipoEndereco(TipoEndereco.ENTREGA);
		endereco2.setUf("PR");
		endereco2.setCidade("Curitiba");
		endereco2.setEmpresa(pessoaJuridica);
		
		pessoaFisica.getEnderecos().add(endereco2);
		pessoaFisica.getEnderecos().add(endereco1);

		pessoaController.salvarPf(pessoaFisica);
	}
}
