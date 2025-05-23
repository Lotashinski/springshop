package com.github.lotashinski.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.github.lotashinski.dto.OrderCollectionItemDto;
import com.github.lotashinski.dto.OrderCriteriaDto;
import com.github.lotashinski.dto.OrderDataDto;
import com.github.lotashinski.dto.OrderDto;
import com.github.lotashinski.exceptions.ResourceNotFoundException;
import com.github.lotashinski.service.OrderService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/orders", produces = MediaType.APPLICATION_JSON_VALUE)
public class OrderController {

	private final OrderService service;
	
	/**
	 * Load by criteria ({@link OrderCriteriaDto}) orders and sort them by {@code title} 
	 * 
	 * @param criteria use as a condition. {@code null} fields do not limit the result
	 * @return sorted collection of orders ({@link OrderCollectionItemDto})
	 */
	@GetMapping
	public ResponseEntity<List<? extends OrderCollectionItemDto>> findByCriteria(OrderCriteriaDto criteria) {
		return ResponseEntity.ok(service.findByCriteria(criteria));
	}
	
	/**
	 * Create new order
	 * 
	 * @param data data for {@link Order}
	 * @return created entity as {@link OrderDto} 
	 */
	@PostMapping
	public ResponseEntity<OrderDto> create(@RequestBody OrderDataDto data) {
		return new ResponseEntity<>(service.create(data), HttpStatus.CREATED);
	}
	
	/**
	 * Get order via identifier
	 * 
	 * @throws ResourceNotFoundException if {@link Order} not found
	 * @param id identifier of {@link Order}
	 * @return order as {@link OrderDto}
	 */
	@GetMapping(path = "/{id:\\d+}")
	public ResponseEntity<OrderDto> read(@PathVariable("id") Long id) {
		return ResponseEntity.ok(service.read(id));
	}
	
	/**
	 * Update order via identifier
	 * 
	 * @throws ResourceNotFoundException if {@link Order} not found
	 * @param id identifier of {@link Order}
	 * @param data data for {@link Order}
	 * @return order as {@link OrderDto}
	 */
	@PutMapping(path = "/{id:\\d+}")
	public ResponseEntity<OrderDto> update(@PathVariable("id") Long id, @RequestBody OrderDataDto data) {
		return ResponseEntity.ok(service.update(id, data));
	}
	
	/**
	 * Delete order via identifier
	 * 
	 * @throws ResourceNotFoundException if {@link Order} not found
	 * @param id identifier of {@link Order}
	 */
	@DeleteMapping(path = "/{id:\\d+}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") Long id) {
		service.delete(id);
	}
	
}
