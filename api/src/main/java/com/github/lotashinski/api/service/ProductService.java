package com.github.lotashinski.api.service;

import java.util.List;

import com.github.lotashinski.api.dto.ProductCollectionItemDto;
import com.github.lotashinski.api.dto.ProductCriteriaDto;
import com.github.lotashinski.api.dto.ProductDataDto;
import com.github.lotashinski.api.dto.ProductDto;
import com.github.lotashinski.api.entity.Product;
import com.github.lotashinski.api.exceptions.ResourceNotFoundException;

/**
 * CRUD service for working with {@code Product}
 * 
 * @see Product
 * @see ProductCollectionItemDto
 * @see ProductDto
 * @see ProductDataDto
 */
public interface ProductService {
	
	/**
	 * Get product that satisfy the criteria
	 * 
	 * @param criteria
	 * @return
	 */
	List<? extends ProductCollectionItemDto> findByCriteria(ProductCriteriaDto criteria);
	
	/**
	 * Create new product
	 * 
	 * @param dto
	 * @return
	 */
	ProductDto create(ProductDataDto dto);
	
	/**
	 * Load product via identifier
	 * 
	 * @throws ResourceNotFoundException resource not found
	 * @param id
	 * @return
	 */
	ProductDto read(Long id);
	
	/**
	 * Update product via identifier
	 * 
	 * @throws ResourceNotFoundException resource not found
	 * @param id
	 * @param data
	 * @return
	 */
	ProductDto update(Long id, ProductDataDto data);
	
	/**
	 * Delete product via id
	 * 
	 * @throws ResourceNotFoundException resource not found
	 * @param id
	 */
	void delete(Long id);
	
}
