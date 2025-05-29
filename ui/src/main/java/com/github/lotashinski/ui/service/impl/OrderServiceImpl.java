package com.github.lotashinski.ui.service.impl;

import java.util.Collection;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.github.lotashinski.api.dto.OrderDataDto;
import com.github.lotashinski.api.dto.OrderDto;
import com.github.lotashinski.api.dto.OrderItemDataDto;
import com.github.lotashinski.ui.client.OrderClient;
import com.github.lotashinski.ui.service.OrderService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

	private final OrderClient orderClient;
	
	@Override
	public OrderDto createOrder(String customerName, String address, Map<Long, Integer> products) {
		log.debug("Create order {} {} {}", customerName, address, products);
		
		OrderDataDto dto = new OrderDataDto();
		dto.setCustomerName(customerName);
		dto.setAddress(address);
		dto.setItems(generateOrderSet(products));
		
		return orderClient.create(dto);
	}

	private static Collection<? extends OrderItemDataDto> generateOrderSet(Map<Long, Integer> products) {
		return products.entrySet()
			.stream()
			.map(i -> generateOrderItem(i.getKey(), i.getValue()))
			.toList();
	}

	private static OrderItemDataDto generateOrderItem(Long productId, Integer count) {
		OrderItemDataDto item = new OrderItemDataDto();
		item.setProductId(productId);
		item.setCount(count);
		
		return item;
	}
	
}
