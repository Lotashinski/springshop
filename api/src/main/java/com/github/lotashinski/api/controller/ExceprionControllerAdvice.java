package com.github.lotashinski.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.github.lotashinski.api.dto.ErrorDto;
import com.github.lotashinski.api.exceptions.ResourceNotFoundException;

@RestControllerAdvice
public class ExceprionControllerAdvice {
	
	private static final String RESOURCE_NOT_FOUND = "RESOURCE_NOT_FOUND";
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorDto> handleNotFoundException(ResourceNotFoundException ex) {
        ErrorDto error = new ErrorDto(RESOURCE_NOT_FOUND, ex.getMessage(), null);
        
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
	
}
