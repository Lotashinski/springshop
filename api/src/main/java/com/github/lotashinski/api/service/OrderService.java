package com.github.lotashinski.api.service;

import java.util.List;

import com.github.lotashinski.api.dto.OrderCollectionItemDto;
import com.github.lotashinski.api.dto.OrderCriteriaDto;
import com.github.lotashinski.api.dto.OrderDataDto;
import com.github.lotashinski.api.dto.OrderDto;
import com.github.lotashinski.api.dto.OrderItemDto;
import com.github.lotashinski.api.exceptions.ResourceNotFoundException;

/**
 * CRUD service for working with {@code Product}
 * 
 * @see Order
 * @see OrderCollectionItemDto
 * @see OrderItemDto
 * @see OrderDto
 * @see OrderDataDto
 */
public interface OrderService {
	
	/**
	 * Get order that satisfy the criteria
	 * 
	 * @param criteria
	 * @return
	 */
	List<? extends OrderCollectionItemDto> findByCriteria(OrderCriteriaDto criteria);
	
	/**
	 * Create new order
	 * 
	 * @param dto
	 * @return
	 */
	OrderDto create(OrderDataDto dto);
	
	/**
	 * Load order via identifier
	 * 
	 * @throws ResourceNotFoundException resource not found
	 * @param id
	 * @return
	 */
	OrderDto read(Long id);
	
	/**
	 * Update order via identifier
	 * 
	 * @throws ResourceNotFoundException resource not found
	 * @param id
	 * @param data
	 * @return
	 */
	OrderDto update(Long id, OrderDataDto data);
	
	/**
	 * Delete order via id
	 * 
	 * @throws ResourceNotFoundException resource not found
	 * @param id
	 */
	void delete(Long id);
	
}
