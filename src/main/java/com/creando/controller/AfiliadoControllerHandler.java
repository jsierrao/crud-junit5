package com.creando.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.creando.dto.AfiliadoResponse;
import com.creando.excepciones.Excepciones;

@RestControllerAdvice
public class AfiliadoControllerHandler extends ResponseEntityExceptionHandler {
	
	
	@ExceptionHandler(Excepciones.class)
	public ResponseEntity<AfiliadoResponse>manejoError(RuntimeException ex){
		AfiliadoResponse rsp = AfiliadoResponse.builder().code(String.valueOf(HttpStatus.BAD_REQUEST))
				.errores(ex.getMessage()).build();
		return new ResponseEntity<>(rsp,HttpStatus.BAD_REQUEST) ;
	}
}
