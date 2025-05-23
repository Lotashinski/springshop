package com.github.lotashinski.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.github.lotashinski.dto.ProductCollectionItemDto;
import com.github.lotashinski.dto.ProductCriteriaDto;
import com.github.lotashinski.dto.ProductDataDto;
import com.github.lotashinski.dto.ProductDto;
import com.github.lotashinski.exceptions.ResourceNotFoundException;

public interface ProductController {

	/**
	 * Load by criteria ({@link ProductCriteriaDto}) products and sort them by {@code title} 
	 * 
	 * @param criteria use as a condition. {@code null} fields do not limit the result
	 * @return sorted collection of products ({@link ProductCollectionItemDto})
	 */
	ResponseEntity<List<? extends ProductCollectionItemDto>> findByCriteria(ProductCriteriaDto criteria);
	
	/**
	 * Create new product
	 * 
	 * @param data data for {@link Product}
	 * @return created entity as {@link ProductDto} 
	 */
	ResponseEntity<ProductDto> create(ProductDataDto data);
	
	/**
	 * Get product via identifier
	 * 
	 * @throws ResourceNotFoundException if {@link Product} not found
	 * @param id identifier of {@link Product}
	 * @return order as {@link ProductDto}
	 */
	ResponseEntity<ProductDto> read(Long id);
	
	/**
	 * Update product via identifier
	 * 
	 * @throws ResourceNotFoundException if {@link Product} not found
	 * @param id identifier of {@link Product}
	 * @param data data for {@link Product}
	 * @return order as {@link ProductDto}
	 */
	ResponseEntity<ProductDto> update(Long id, ProductDataDto data);
	
	/**
	 * Delete product via identifier
	 * 
	 * @throws ResourceNotFoundException if {@link Product} not found
	 * @param id identifier of {@link Product}
	 */
	void delete(Long id);
	
}
