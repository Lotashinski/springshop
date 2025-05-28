package com.github.lotashinski.ui.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;

import com.github.lotashinski.ui.service.BucketService;
import com.github.lotashinski.ui.service.CategoriesService;
import com.github.lotashinski.ui.service.ProductService;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequiredArgsConstructor
public class IndexController {

	private final CategoriesService categoryService;
	
	private final ProductService productService;
	
	@Setter
	@Autowired
	private BucketService bucketService;
	
	@GetMapping({"/", "index"})
	public String getMethodName(Model model, @RequestParam(name = "categories", required = false) Set<Long> categories) {
		log.debug("Show products for categories [{}]", categories);
		
		log.debug("RequestContext active: {}", 
			    RequestContextHolder.getRequestAttributes() != null);
		
		model.addAttribute("categories", categoryService.getAll());
		model.addAttribute("products", productService.getAll());
		model.addAttribute("selected", categories == null ? Set.of() : categories);
		model.addAttribute("bucket", bucketService.getProducts());
		
		return "main";
	}
	
}
