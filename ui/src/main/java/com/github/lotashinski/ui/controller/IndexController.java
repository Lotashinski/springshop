package com.github.lotashinski.ui.controller;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.lotashinski.ui.service.CartService;
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
	
	private final CartService cartService;
	
	@GetMapping({"/", "index"})
	public String getMethodName(Model model, @RequestParam(name = "categories", required = false) Set<Long> categories) {
		log.debug("Show products for categories [{}]", categories);
		
		model.addAttribute("queryString", convertCategoriesToQueryString(categories));
		model.addAttribute("categories", categoryService.getAll());
		model.addAttribute("products", productService.getAll(categories));
		model.addAttribute("selected", categories == null ? Set.of() : categories);
		model.addAttribute("cart", cartService.getProductIds());
		
		return "main";
	}
	
	@PostMapping("/{id}")
	public String addProduct(@PathVariable("id") Long id, @RequestParam(name = "categories", required = false) Set<Long> categories) {
		cartService.putProduct(id, 1);
		
		return "redirect:/" + convertCategoriesToQueryString(categories);
	}
	
	private static String convertCategoriesToQueryString(Set<Long> categories) {
		if (categories == null || categories.isEmpty()) return "";
		return "?" +  categories.stream().map(i -> "categories=" + i).collect(Collectors.joining("&"));
	}
	
}
