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
	
	public PessoaService(PessoaRepository pessoaRepository, UsuarioRepository usuarioRepository,
			JdbcTemplate jdbcTemplate, PasswordEncoder passwordEncoder) {
		this.pessoaRepository = pessoaRepository;
		this.usuarioRepository = usuarioRepository;
		this.jdbcTemplate = jdbcTemplate;
		this.passwordEncoder = passwordEncoder;
	}

	public PessoaJuridica salvarPj(PessoaJuridica pessoaJuridica) {
		
		pessoaJuridica = pessoaRepository.save(pessoaJuridica);
		
		Usuario usuarioPj = usuarioRepository.findUserByPessoa(pessoaJuridica.getId(), pessoaJuridica.getEmail());
		
		if(usuarioPj == null) {
			String constraint = usuarioRepository.consultarConstraintAcesso();
			
			if(constraint != null) {
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
		}
		return pessoaJuridica;
	}
}
