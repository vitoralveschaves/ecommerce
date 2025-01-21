package com.example.ecommerce.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.ecommerce.exception.TokenInvalidoException;
import com.example.ecommerce.model.Usuario;
import com.example.ecommerce.repository.UsuarioRepository;

@Component
public class SecurityFilter extends OncePerRequestFilter {
	
	private final JwtTokenService tokenService;
	private final UsuarioRepository usuarioRepository;

	public SecurityFilter(JwtTokenService tokenService, UsuarioRepository usuarioRepository) {
		this.tokenService = tokenService;
		this.usuarioRepository = usuarioRepository;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		try {
			String token = request.getHeader("Authorization");
			
			if(token != null) {
				String username = tokenService.verifyToken(token);
				Usuario usuario = usuarioRepository.findUserByLogin(username);
				UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
				SecurityContextHolder.getContext().setAuthentication(auth);
			}
			
			filterChain.doFilter(request, response);
		} catch (TokenInvalidoException e) {
		    response.setStatus(HttpServletResponse.SC_FORBIDDEN);
		    response.setCharacterEncoding("UTF-8");
		    response.getWriter().write("{\"message\": \"" + e.getLocalizedMessage() + "\"}");
		}
	}
}
