package com.github.lotashinski.ui.service.impl;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.github.lotashinski.api.dto.OrderCollectionItemDto;
import com.github.lotashinski.api.dto.OrderCriteriaDto;
import com.github.lotashinski.api.dto.OrderDataDto;
import com.github.lotashinski.api.dto.OrderDto;
import com.github.lotashinski.api.dto.OrderItemDataDto;
import com.github.lotashinski.ui.client.OrderClient;
import com.github.lotashinski.ui.service.CartService;
import com.github.lotashinski.ui.service.OrderService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

	private final OrderClient orderClient;
	
	private final CartService cartService;
	
	@Override
	public OrderDto createOrder(String customerName, String address, Map<Long, Integer> products) {
		log.debug("Create order {} {} {}", customerName, address, products);
		
		OrderDataDto dto = new OrderDataDto();
		dto.setCustomerName(customerName);
		dto.setAddress(address);
		dto.setItems(generateOrderSet(products));
		
		cartService.clear();
		
		return orderClient.create(dto);
	}

	@Override
	public List<? extends OrderCollectionItemDto> findByCriteria(OrderCriteriaDto criteria) {
		log.debug("Find orders by criteria {}", criteria);
		
		return orderClient.findByCriteria(criteria);
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
