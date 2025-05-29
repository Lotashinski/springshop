package com.github.lotashinski.ui.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.lotashinski.api.dto.OrderDto;
import com.github.lotashinski.ui.service.OrderService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

	private final OrderService orderService;

	@PostMapping
	public String create(@RequestParam(name = "customerName") String customerName, 
			@RequestParam(name = "address") String address, 
			@RequestParam(name = "products", required = false) Map<Long, Integer> products) {
		OrderDto order = orderService.createOrder(customerName, address, products);

		return "redirect:/orders/" + order.getId();
	}

}
