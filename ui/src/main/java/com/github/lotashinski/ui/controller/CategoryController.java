package com.github.lotashinski.ui.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.lotashinski.api.dto.CategoryDataDto;
import com.github.lotashinski.api.dto.CategoryDto;
import com.github.lotashinski.ui.service.CategoryService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {

	private final CategoryService categoriesService;
	
	@GetMapping
	public String index(Model model) {
		model.addAttribute("categories", categoriesService.getAll());
		
		return "categories";
	}
	
	@GetMapping(path = "/create")
	public String createPage(Model model) {
		model.addAttribute("category", new CategoryDto());
		
		return "category_edit";
	}
	
	@PostMapping(path = "/create")
	public String create(CategoryDataDto data) {
		CategoryDto category = categoriesService.create(data);
		
		return "redirect:/categories/" + category.getId(); 
	}
	
	@GetMapping(path = "/{id:\\d+}")
	public String updatePage(Model model, @PathVariable("id") Long id) {
		model.addAttribute("category", categoriesService.getById(id));
		
		return "category_edit";
	}
	
	@PostMapping(path = "/{id:\\d+}")
	public String edit(CategoryDataDto data, @PathVariable("id") Long id) {
		categoriesService.update(id, data);
		
		return "redirect:/categories/" + id;
	}
	
	@PostMapping(path = "/{id:\\d+}/delete")
	@DeleteMapping(path = "/{id:\\d+}/delete")
	public String delete(@PathVariable("id") Long id) {
		categoriesService.delete(id);
		
		return "redirect:/categories";
	}
	
}
