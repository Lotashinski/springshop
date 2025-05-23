package com.github.lotashinski.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.github.lotashinski.dto.OrderCollectionItemDto;
import com.github.lotashinski.dto.OrderCriteriaDto;
import com.github.lotashinski.dto.OrderDataDto;
import com.github.lotashinski.dto.OrderDto;
import com.github.lotashinski.exceptions.ResourceNotFoundException;

public interface OrderController {
	
	/**
	 * Load by criteria ({@link OrderCriteriaDto}) orders and sort them by {@code title} 
	 * 
	 * @param criteria use as a condition. {@code null} fields do not limit the result
	 * @return sorted collection of orders ({@link OrderCollectionItemDto})
	 */
	ResponseEntity<List<? extends OrderCollectionItemDto>> findByCriteria(OrderCriteriaDto criteria);
	
	/**
	 * Create new order
	 * 
	 * @param data data for {@link Order}
	 * @return created entity as {@link OrderDto} 
	 */
	ResponseEntity<OrderDto> create(OrderDataDto data);
	
	/**
	 * Get order via identifier
	 * 
	 * @throws ResourceNotFoundException if {@link Order} not found
	 * @param id identifier of {@link Order}
	 * @return order as {@link OrderDto}
	 */
	ResponseEntity<OrderDto> read(Long id);
	
	/**
	 * Update order via identifier
	 * 
	 * @throws ResourceNotFoundException if {@link Order} not found
	 * @param id identifier of {@link Order}
	 * @param data data for {@link Order}
	 * @return order as {@link OrderDto}
	 */
	ResponseEntity<OrderDto> update(Long id, OrderDataDto data);
	
	/**
	 * Delete order via identifier
	 * 
	 * @throws ResourceNotFoundException if {@link Order} not found
	 * @param id identifier of {@link Order}
	 */
	void delete(Long id);
	
}
