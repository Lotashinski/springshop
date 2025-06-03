package com.github.lotashinski.ui.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import feign.FeignException;

@ControllerAdvice
public class ExceptionController {
	
	@ExceptionHandler(FeignException.class)
	public String handleNotFoundException(FeignException ex, Model model) {
        model.addAttribute("code", ex.status());
		
        return "error";
    }
	
}
