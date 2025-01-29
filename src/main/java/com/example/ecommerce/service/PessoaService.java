package com.example.ecommerce.service;

import java.util.Calendar;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.ecommerce.model.PessoaFisica;
import com.example.ecommerce.model.PessoaJuridica;
import com.example.ecommerce.model.Usuario;
import com.example.ecommerce.repository.PessoaFisicaRepository;
import com.example.ecommerce.repository.PessoaJuridicaRepository;
import com.example.ecommerce.repository.UsuarioRepository;

@Service
public class PessoaService {

	private final PessoaJuridicaRepository pessoaJuridicaRepository;
	private final PessoaFisicaRepository pessoaFisicaRepository;
	private final UsuarioRepository usuarioRepository;
	private final JdbcTemplate jdbcTemplate;
	private final PasswordEncoder passwordEncoder;
	private final EmailService emailService;

	public PessoaService(PessoaJuridicaRepository pessoaJuridicaRepository, UsuarioRepository usuarioRepository,
			JdbcTemplate jdbcTemplate, PasswordEncoder passwordEncoder,
			EmailService emailService, PessoaFisicaRepository pessoaFisicaRepository) {
		this.pessoaJuridicaRepository = pessoaJuridicaRepository;
		this.pessoaFisicaRepository = pessoaFisicaRepository;
		this.usuarioRepository = usuarioRepository;
		this.jdbcTemplate = jdbcTemplate;
		this.passwordEncoder = passwordEncoder;
		this.emailService = emailService;
	}

	public PessoaJuridica salvarPj(PessoaJuridica pessoaJuridica) {

		for (int i = 0; i < pessoaJuridica.getEnderecos().size(); i++) {
			pessoaJuridica.getEnderecos().get(i).setPessoa(pessoaJuridica);
			pessoaJuridica.getEnderecos().get(i).setEmpresa(pessoaJuridica);
		}

		pessoaJuridica = pessoaJuridicaRepository.save(pessoaJuridica);

		Usuario usuarioPj = usuarioRepository.findUserByPessoa(pessoaJuridica.getId(), pessoaJuridica.getEmail());

		if (usuarioPj == null) {
			String constraint = usuarioRepository.consultarConstraintAcesso();

			if (constraint != null) {
				jdbcTemplate.execute("begin; alter table usuarios_acesso drop constraint " + constraint + "; commit;");
			}

			usuarioPj = new Usuario();
			usuarioPj.setDataAtualSenha(Calendar.getInstance().getTime());
			usuarioPj.setEmpresa(pessoaJuridica);
			usuarioPj.setPessoa(pessoaJuridica);
			usuarioPj.setLogin(pessoaJuridica.getEmail());

			String senha = String.valueOf(Calendar.getInstance().getTimeInMillis());
			String senhaCriptografada = passwordEncoder.encode(senha);

			usuarioPj.setSenha(senhaCriptografada);

			usuarioPj = usuarioRepository.save(usuarioPj);

			usuarioRepository.insereAcessoUser(usuarioPj.getId());

			StringBuilder menssagemHtml = new StringBuilder();

			menssagemHtml.append("<b>Segue abaixo seus dados de acesso</b><br/><br/>");
			menssagemHtml.append("<b>Login: </b>" + pessoaJuridica.getEmail() + "<br/><br/>");
			menssagemHtml.append("<b>Senha: </b>").append(senha).append("<br/><br/>");
			menssagemHtml.append("Obrigado!");

			try {
				emailService.enviarEmailHtml("Acesso Gerado para o Ecommerce Java", menssagemHtml.toString(), pessoaJuridica.getEmail());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return pessoaJuridica;
	}

	public PessoaFisica salvarPf(PessoaFisica pessoaFisica) {
		for (int i = 0; i < pessoaFisica.getEnderecos().size(); i++) {
			pessoaFisica.getEnderecos().get(i).setPessoa(pessoaFisica);
		}

		pessoaFisica = pessoaFisicaRepository.save(pessoaFisica);

		Usuario usuarioPf = usuarioRepository.findUserByPessoa(pessoaFisica.getId(), pessoaFisica.getEmail());

		if (usuarioPf == null) {
			String constraint = usuarioRepository.consultarConstraintAcesso();

			if (constraint != null) {
				jdbcTemplate.execute("begin; alter table usuarios_acesso drop constraint " + constraint + "; commit;");
			}

			usuarioPf = new Usuario();
			usuarioPf.setDataAtualSenha(Calendar.getInstance().getTime());
			usuarioPf.setEmpresa(pessoaFisica.getEmpresa());
			usuarioPf.setPessoa(pessoaFisica);
			usuarioPf.setLogin(pessoaFisica.getEmail());

			String senha = String.valueOf(Calendar.getInstance().getTimeInMillis());
			String senhaCriptografada = passwordEncoder.encode(senha);

			usuarioPf.setSenha(senhaCriptografada);

			usuarioPf = usuarioRepository.save(usuarioPf);

			usuarioRepository.insereAcessoUser(usuarioPf.getId());

			StringBuilder menssagemHtml = new StringBuilder();

			menssagemHtml.append("<b>Segue abaixo seus dados de acesso</b><br/><br/>");
			menssagemHtml.append("<b>Login: </b>" + pessoaFisica.getEmail() + "<br/><br/>");
			menssagemHtml.append("<b>Senha: </b>").append(senha).append("<br/><br/>");
			menssagemHtml.append("Obrigado!");

			try {
				emailService.enviarEmailHtml("Acesso Gerado para o Ecommerce Java", menssagemHtml.toString(), pessoaFisica.getEmail());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return pessoaFisica;
	}
}
