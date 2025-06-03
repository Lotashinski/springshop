package com.github.lotashinski.ui.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.lotashinski.ui.service.CartService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {

	private final CartService cartService;
	
	@GetMapping
	public String getCart(Model model) {
		model.addAttribute("products", cartService.getProducts());
		
		return "cart";
	}
	
}
