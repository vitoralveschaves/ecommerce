package com.example.ecommerce.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.ecommerce.dto.MethodArgumentNotValidErrorDto;
import com.example.ecommerce.dto.ObjetoErroDto;
import com.example.ecommerce.dto.ObjetoValidationDto;

@RestControllerAdvice
public class ControleExcecoes {
	
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<MethodArgumentNotValidErrorDto> methodArgumentNotValidHandler(MethodArgumentNotValidException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new MethodArgumentNotValidErrorDto(HttpStatus.BAD_REQUEST, exceptionToList(exception)));
    }
	
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
	
    private List<ObjetoValidationDto> exceptionToList(MethodArgumentNotValidException exception) {
        List<ObjetoValidationDto> errors = new ArrayList<>();
        exception.getFieldErrors().forEach(e -> {
            errors.add(new ObjetoValidationDto(e.getField(), e.getDefaultMessage()));
        });
        return errors;
    }
}
