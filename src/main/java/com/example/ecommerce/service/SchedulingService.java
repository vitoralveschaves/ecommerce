package com.example.ecommerce.service;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.mail.MessagingException;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.example.ecommerce.model.Usuario;
import com.example.ecommerce.repository.UsuarioRepository;

@Service
public class SchedulingService {
	
	private final UsuarioRepository usuarioRepository;
	private final EmailService emailService;

	public SchedulingService(UsuarioRepository usuarioRepository, EmailService emailService) {
		this.usuarioRepository = usuarioRepository;
		this.emailService = emailService;
	}
	
	@Scheduled(cron = "0 0 12 * * *", zone = "America/Sao_Paulo")
	public void notifyUserChangePassword() throws UnsupportedEncodingException, MessagingException, InterruptedException {
		List<Usuario> usuarios = usuarioRepository.usuarioSenhaAtualizar();
		for(Usuario usuario : usuarios) {
			StringBuilder message = new StringBuilder();
			message.append("Olá, ").append(usuario.getPessoa().getNome()).append("<br/>")
			.append("Está na hora de atualizar sua senha.").append("<br/>")
			.append("Recomendamos tal ação a cada 90 dias para garantir a segurança de seus dados.").append("<br/><br/>")
			.append("Ecommerce Java");
			emailService.enviarEmailHtml("Atualização de senha", message.toString(), usuario.getUsername());
			Thread.sleep(4000);
		}
	}
}
