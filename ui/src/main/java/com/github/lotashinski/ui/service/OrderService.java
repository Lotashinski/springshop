package com.github.lotashinski.ui.service;

import java.util.List;
import java.util.Map;

import com.github.lotashinski.api.dto.OrderCollectionItemDto;
import com.github.lotashinski.api.dto.OrderCriteriaDto;
import com.github.lotashinski.api.dto.OrderDto;

import feign.QueryMap;

public interface OrderService {

	OrderDto createOrder(String customerName, String address, Map<Long, Integer> products);
	
	List<? extends OrderCollectionItemDto> findByCriteria(@QueryMap  OrderCriteriaDto criteria);
	
	OrderDto getById(Long id);
	
}
