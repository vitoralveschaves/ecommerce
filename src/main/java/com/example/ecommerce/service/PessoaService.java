package com.example.ecommerce.service;

import java.util.Calendar;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.ecommerce.model.PessoaJuridica;
import com.example.ecommerce.model.Usuario;
import com.example.ecommerce.repository.PessoaRepository;
import com.example.ecommerce.repository.UsuarioRepository;

@Service
public class PessoaService {

	private final PessoaRepository pessoaRepository;
	private final UsuarioRepository usuarioRepository;
	private final JdbcTemplate jdbcTemplate;
	private final PasswordEncoder passwordEncoder;
	private final EmailService emailService;

	public PessoaService(PessoaRepository pessoaRepository, UsuarioRepository usuarioRepository,
			JdbcTemplate jdbcTemplate, PasswordEncoder passwordEncoder, EmailService emailService) {
		this.pessoaRepository = pessoaRepository;
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

		pessoaJuridica = pessoaRepository.save(pessoaJuridica);

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

			usuarioRepository.insereAcessoPj(usuarioPj.getId());

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
}
