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

import com.github.lotashinski.dto.ProductCollectionItemDto;
import com.github.lotashinski.dto.ProductCriteriaDto;
import com.github.lotashinski.dto.ProductDataDto;
import com.github.lotashinski.dto.ProductDto;
import com.github.lotashinski.exceptions.ResourceNotFoundException;
import com.github.lotashinski.service.ProductService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/products", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductController {

	private final ProductService service;
	
	/**
	 * Load by criteria ({@link ProductCriteriaDto}) products and sort them by {@code title} 
	 * 
	 * @param criteria use as a condition. {@code null} fields do not limit the result
	 * @return sorted collection of products ({@link ProductCollectionItemDto})
	 */
	@GetMapping
	public ResponseEntity<List<? extends ProductCollectionItemDto>> findByCriteria(ProductCriteriaDto criteria) {
		return ResponseEntity.ok(service.findByCriteria(criteria));
	}
	
	/**
	 * Create new product
	 * 
	 * @param data data for {@link Product}
	 * @return created entity as {@link ProductDto} 
	 */
	@PostMapping
	public ResponseEntity<ProductDto> create(@RequestBody ProductDataDto data) {
		return new ResponseEntity<>(service.create(data), HttpStatus.CREATED);
	}
	
	/**
	 * Get product via identifier
	 * 
	 * @throws ResourceNotFoundException if {@link Product} not found
	 * @param id identifier of {@link Product}
	 * @return order as {@link ProductDto}
	 */
	@GetMapping(path = "/{id:\\d+}")
	public ResponseEntity<ProductDto> read(@PathVariable("id") Long id) {
		return ResponseEntity.ok(service.read(id));
	}
	
	/**
	 * Update product via identifier
	 * 
	 * @throws ResourceNotFoundException if {@link Product} not found
	 * @param id identifier of {@link Product}
	 * @param data data for {@link Product}
	 * @return order as {@link ProductDto}
	 */
	@PutMapping(path = "/{id:\\d+}")
	public ResponseEntity<ProductDto> update(@PathVariable("id") Long id, @RequestBody ProductDataDto data) {
		return ResponseEntity.ok(service.update(id, data));
	}
	
	/**
	 * Delete product via identifier
	 * 
	 * @throws ResourceNotFoundException if {@link Product} not found
	 * @param id identifier of {@link Product}
	 */
	@DeleteMapping(path = "/{id:\\d+}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") Long id) {
		service.delete(id);
	}
	
}
