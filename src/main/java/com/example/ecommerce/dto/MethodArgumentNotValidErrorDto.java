package com.example.ecommerce.dto;
import java.util.List;
import org.springframework.http.HttpStatus;

public class MethodArgumentNotValidErrorDto {
	
	public HttpStatus status;
	
	List<ObjetoValidationDto> errors;

	public MethodArgumentNotValidErrorDto(HttpStatus status, List<ObjetoValidationDto> errors) {
		super();
		this.status = status;
		this.errors = errors;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public List<ObjetoValidationDto> getErrors() {
		return errors;
	}

	public void setErrors(List<ObjetoValidationDto> errors) {
		this.errors = errors;
	}
}
