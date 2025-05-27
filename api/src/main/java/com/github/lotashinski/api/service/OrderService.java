package com.github.lotashinski.api.service;

import java.util.List;

import com.github.lotashinski.api.dto.OrderCollectionItemDto;
import com.github.lotashinski.api.dto.OrderCriteriaDto;
import com.github.lotashinski.api.dto.OrderDataDto;
import com.github.lotashinski.api.dto.OrderDto;

public interface OrderService {
	
	List<? extends OrderCollectionItemDto> findByCriteria(OrderCriteriaDto criteria);
	
	OrderDto create(OrderDataDto dto);
	
	OrderDto read(Long id);
	
	OrderDto update(Long id, OrderDataDto data);
	
	void delete(Long id);
	
}
