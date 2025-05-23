package com.github.lotashinski.service;

import java.util.List;

import com.github.lotashinski.dto.OrderCollectionItemDto;
import com.github.lotashinski.dto.OrderCriteriaDto;
import com.github.lotashinski.dto.OrderDataDto;
import com.github.lotashinski.dto.OrderDto;

public interface OrderService {
	
	List<? extends OrderCollectionItemDto> findByCriteria(OrderCriteriaDto criteria);
	
	OrderDto create(OrderDataDto dto);
	
	OrderDto read(Long id);
	
	OrderDto update(Long id, OrderDataDto data);
	
	void delete(Long id);
	
}
