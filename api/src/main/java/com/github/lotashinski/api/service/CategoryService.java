package com.github.lotashinski.api.service;

import java.util.List;

import com.github.lotashinski.api.dto.CategoryCollectionItemDto;
import com.github.lotashinski.api.dto.CategoryCriteriaDto;
import com.github.lotashinski.api.dto.CategoryDataDto;
import com.github.lotashinski.api.dto.CategoryDto;
import com.github.lotashinski.api.entity.Category;
import com.github.lotashinski.api.exceptions.ResourceNotFoundException;

/**
 * CRUD service for working with {@code Category}
 * 
 * @see Category
 * @see CategoryCollectionItemDto
 * @see CategoryDto
 * @see CategoryDataDto
 */
public interface CategoryService {

	/**
	 * Get categories that satisfy the criteria
	 * 
	 * @param criteria
	 * @return
	 */
	List<? extends CategoryCollectionItemDto> findByCriteria(CategoryCriteriaDto criteria);
	
	/**
	 * Create new category
	 * 
	 * @param dto
	 * @return
	 */
	CategoryDto create(CategoryDataDto dto);
	
	/**
	 * Load category via identifier
	 * 
	 * @throws ResourceNotFoundException resource not found
	 * @param id
	 * @return
	 */
	CategoryDto read(Long id);
	
	/**
	 * Update category via identifier
	 * 
	 * @throws ResourceNotFoundException resource not found
	 * @param id
	 * @param data
	 * @return
	 */
	CategoryDto update(Long id, CategoryDataDto data);
	
	/**
	 * Delete category via id
	 * 
	 * @throws ResourceNotFoundException resource not found
	 * @param id
	 */
	void delete(Long id);
	
}
