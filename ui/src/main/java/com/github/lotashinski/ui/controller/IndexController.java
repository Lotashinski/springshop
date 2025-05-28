package com.github.lotashinski.ui.controller;

import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.lotashinski.ui.service.CategoriesService;
import com.github.lotashinski.ui.service.ProductService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequiredArgsConstructor
public class IndexController {

	private final CategoriesService categoryService;
	
	private final ProductService productService;
	
	@GetMapping({"/", "index"})
	public String getMethodName(Model model, @RequestParam(name = "categories", required = false) Set<Long> categories) {
		log.debug("Show products for categories [{}]", categories);
		
		model.addAttribute("categories", categoryService.getAll());
		model.addAttribute("products", productService.getAll());
		model.addAttribute("selected", categories == null ? Set.of() : categories);
		
		return "main";
	}
	
}
