package com.example.ecommerce.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.ecommerce.dto.ObjetoErroDto;

@RestControllerAdvice
public class ControleExcecoes {
	
	@ExceptionHandler(BadCredentialsException.class)
	public ResponseEntity<ObjetoErroDto> handleBadCredentialsException(BadCredentialsException e) {
		ObjetoErroDto response = new ObjetoErroDto(e.getLocalizedMessage(), HttpStatus.UNAUTHORIZED.name());
		return new ResponseEntity<ObjetoErroDto>(response, HttpStatus.UNAUTHORIZED);
	}
	
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<ObjetoErroDto> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
		ObjetoErroDto response = new ObjetoErroDto("Não está sendo enviado dados para o corpo da requisição", HttpStatus.BAD_REQUEST.name());
		return new ResponseEntity<ObjetoErroDto>(response, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(CustomException.class)
	public ResponseEntity<ObjetoErroDto> handleCustomException(CustomException e) {
		ObjetoErroDto response = new ObjetoErroDto(e.getMessage(), HttpStatus.BAD_REQUEST.name());
		return new ResponseEntity<ObjetoErroDto>(response, HttpStatus.BAD_REQUEST);
	}
}
