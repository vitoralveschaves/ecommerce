package com.example.ecommerce.security;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.example.ecommerce.ApplicationContextLoad;
import com.example.ecommerce.model.Usuario;
import com.example.ecommerce.repository.UsuarioRepository;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;

@Service
public class JwtTokenService {
	
	private static final long TIME_EXPIRATION = 259200000;
	private static final String SECRET = "123abc";
	private static final String TOKEN_PREFIX = "Bearer";
	private static final String HEADER_NAME = "Authorization";
	
	public String addAuthentication(HttpServletResponse response, String username) throws Exception{
		
		String jwt = Jwts.builder()
				.setSubject(username)
				.setExpiration(new Date(System.currentTimeMillis() + TIME_EXPIRATION))
				.signWith(SignatureAlgorithm.HS512, SECRET)
				.compact();
		
		String token = TOKEN_PREFIX + " " + jwt;
		
		response.addHeader(HEADER_NAME, token);
		liberacaoCors(response);
		
		return token;
	}
	
	public String verifyToken(String token) {
		try {
			String tokenWithoutBearer = token.replace(TOKEN_PREFIX, "").trim();
			return Jwts.parser()
					.setSigningKey(SECRET)
					.parseClaimsJws(tokenWithoutBearer)
					.getBody()
					.getSubject();
			
		} catch (SignatureException e) {
			return "";
		}
	}
	
	public Authentication getAuthentication(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String token = request.getHeader(HEADER_NAME);
		
		try {
			
			if(token != null) {
				String tokenWithoutBearer = token.replace(TOKEN_PREFIX, "").trim();
				
				String usuarioToken = Jwts.parser()
						.setSigningKey(SECRET)
						.parseClaimsJws(tokenWithoutBearer)
						.getBody()
						.getSubject();
				
				if(usuarioToken != null) {
					Usuario usuario = ApplicationContextLoad.getApplicationContext()
							.getBean(UsuarioRepository.class)
							.findUserByLogin(usuarioToken);
					
					if(usuario != null) {
						return new UsernamePasswordAuthenticationToken(
								usuario.getLogin(),
								usuario.getPassword(),
								usuario.getAuthorities()
						);
					}
				}
			}
			
		} catch (SignatureException e) {
			response.getWriter().write("{\"Error\": \"Token Inválido\"}");
		} catch(ExpiredJwtException e) {
			response.getWriter().write("{\"Error\": \"Token expirado. Efetue o login novamente\"}");
		} finally {
			liberacaoCors(response);
		}
		return null;
	}
	
	private void liberacaoCors(HttpServletResponse response) {
		if(response.getHeader("Access-Control-Allow-Origin") == null) {
			response.addHeader("Access-Control-Allow-Origin", "*");
		}
		
		if(response.getHeader("Access-Control-Allow-Headers") == null) {
			response.addHeader("Access-Control-Allow-Headers", "*");
		}
		
		if(response.getHeader("Access-Control-Request-Headers") == null) {
			response.addHeader("Access-Control-Request-Headers", "*");
		}
		
		if(response.getHeader("Access-Control-Allow-Methods") == null) {
			response.addHeader("Access-Control-Allow-Methods", "*");
		}
	}
}
