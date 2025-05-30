package com.github.lotashinski.ui.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.lotashinski.api.dto.OrderCriteriaDto;
import com.github.lotashinski.api.dto.OrderDto;
import com.github.lotashinski.ui.service.OrderService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

	private final OrderService orderService;
	
	@GetMapping
	public String index(Model model, OrderCriteriaDto criteria) {
		model.addAttribute("orders", orderService.findByCriteria(criteria));
		
		return "orders";
	}
	
	@PostMapping
	public String create(@RequestParam(name = "customerName") String customerName, 
			@RequestParam(name = "address") String address, 
			HttpServletRequest request) {
		OrderDto order = orderService.createOrder(customerName, address, mapToMap(request));

		return "redirect:/orders/" + order.getId();
	}

	private static Map<Long, Integer> mapToMap(HttpServletRequest request) {
		Map<Long, Integer> products = new HashMap<>();
	    request.getParameterMap().forEach((key, values) -> {
	        if (key.startsWith("products[")) {
	            Long productId = Long.parseLong(key.substring(9, key.length() - 1));
	            Integer quantity = Integer.valueOf(values[0]);
	            products.put(productId, quantity);
	        }
	    });
	    
	    return products;
	}
	
}
