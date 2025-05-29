package com.github.lotashinski.ui.service;

import java.util.Map;

import com.github.lotashinski.api.dto.OrderDto;

public interface OrderService {

	OrderDto createOrder(String customerName, String address, Map<Long, Integer> products);
	
}
