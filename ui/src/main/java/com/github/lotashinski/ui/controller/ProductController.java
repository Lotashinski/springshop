package com.github.lotashinski.ui.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.lotashinski.api.dto.ProductDataDto;
import com.github.lotashinski.api.dto.ProductDto;
import com.github.lotashinski.ui.service.CartService;
import com.github.lotashinski.ui.service.CategoryService;
import com.github.lotashinski.ui.service.ProductService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
	
	private final ProductService productService;
	
	private final CategoryService categoryService;
	
	private final CartService cartService;
	
	@GetMapping("/create")
	public String createPage(Model model) {
		ProductDto dto = new ProductDto();
		dto.setCategories(List.of());
		
		model.addAttribute("product", dto);
		model.addAttribute("categories", categoryService.getAll());
		
		return "product_edit";
	}
	
	@PostMapping("/create")
	public String create(ProductDataDto data) {
		ProductDto dto = productService.create(data);
		
		return "redirect:/products/" + dto.getId() + "/edit";
	}
	
	@GetMapping("/{id:\\d+}")
	public String productPage(@PathVariable("id") Long id, Model model) {
		model.addAttribute("product", productService.getById(id));
		model.addAttribute("cart", cartService.getProductIds());
		
		return "product";
	}	
	
	@GetMapping("/{id:\\d+}/edit")
	public String updatePage(@PathVariable("id") Long id, Model model) {
		model.addAttribute("product", productService.getById(id));
		model.addAttribute("categories", categoryService.getAll());
		
		return "product_edit";
	}
	
	@PostMapping("/{id:\\d+}/edit")
	public String update(@PathVariable("id") Long id, ProductDataDto data) {
		ProductDto dto = productService.update(id, data);
		
		return "redirect:/products/" + dto.getId() + "/edit";
	}
	
	@PostMapping(path = "/{id:\\d+}/delete")
	@DeleteMapping(path = "/{id:\\d+}/delete")
	public String delete(@PathVariable("id") Long id) {
		productService.delete(id);
		
		return "redirect:/";
	}
	
}
