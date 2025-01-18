package com.example.ecommerce.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ecommerce.dto.LoginRequestDto;
import com.example.ecommerce.dto.LoginResponseDto;
import com.example.ecommerce.model.Usuario;
import com.example.ecommerce.security.JwtTokenService;

@RestController
@RequestMapping(value = "/login")
public class LoginController {
	
	private final AuthenticationManager authenticationManager;
	private final JwtTokenService tokenService;
	
	public LoginController(AuthenticationManager authenticationManager, JwtTokenService tokenService) {
		this.authenticationManager = authenticationManager;
		this.tokenService = tokenService;
	}

	@PostMapping
	public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto dto, HttpServletResponse response) throws Exception {
		
		UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(dto.getLogin(), dto.getSenha());
		Authentication authentication = authenticationManager.authenticate(auth);
		Usuario usuario = (Usuario) authentication.getPrincipal();
		String token = tokenService.addAuthentication(response, usuario.getUsername());
		
		return ResponseEntity.ok(new LoginResponseDto(token));
	}
}
