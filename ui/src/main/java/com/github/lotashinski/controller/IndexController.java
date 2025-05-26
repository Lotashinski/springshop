package com.github.lotashinski.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.github.lotashinski.service.CategoriesService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class IndexController {

	private final CategoriesService categoryService;
	
	@GetMapping({"/", "index"})
	public String getMethodName(Model model) {
		model.addAttribute("categories", categoryService.getAll());
		
		return "main";
	}
	
}
