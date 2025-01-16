package com.example.ecommerce.security;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.example.ecommerce.ApplicationContextLoad;
import com.example.ecommerce.model.Usuario;
import com.example.ecommerce.repository.UsuarioRepository;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtTokenService {
	
	private static final long TIME_EXPIRATION = 259200000;
	private static final String SECRET = "123abc";
	private static final String TOKEN_PREFIX = "Bearer";
	private static final String HEADER_NAME = "Authorization";
	
	public void addAuthentication(HttpServletResponse response, String username) throws Exception{
		
		String jwt = Jwts.builder()
				.setSubject(username)
				.setExpiration(new Date(System.currentTimeMillis() + TIME_EXPIRATION))
				.signWith(SignatureAlgorithm.HS512, SECRET)
				.compact();
		
		String token = TOKEN_PREFIX + " " + jwt;
		
		response.addHeader(HEADER_NAME, token);
		liberacaoCors(response);
		
		response.getWriter().write("{\"Authorization\": \"" + token + "\"}");
	}
	
	public Authentication getAuthentication(HttpServletRequest request, HttpServletResponse response) {
		String token = request.getHeader(HEADER_NAME);
		
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
		liberacaoCors(response);
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
