package com.github.lotashinski.controller;

import java.util.List;
import java.util.Locale.Category;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import com.github.lotashinski.dto.CategoryCollectionItemDto;
import com.github.lotashinski.dto.CategoryCriteriaDto;
import com.github.lotashinski.dto.CategoryDataDto;
import com.github.lotashinski.dto.CategoryDto;
import com.github.lotashinski.exceptions.ResourceNotFoundException;

public interface CategoryController {
	
	/**
	 * Load by criteria ({@link CategoryCriteriaDto}) categories and sort them by {@code title} 
	 * 
	 * @param criteria use as a condition. {@code null} fields do not limit the result
	 * @return sorted collection of categories ({@link CategoryCollectionItemDto})
	 */
	ResponseEntity<List<? extends CategoryCollectionItemDto>> findByCriteria(CategoryCriteriaDto criteria);
	
	/**
	 * Create new category
	 * 
	 * @param data data for {@link Category}
	 * @return created entity as {@link CategoryDto} 
	 */
	ResponseEntity<CategoryDto> create(@RequestBody CategoryDataDto data);
	
	/**
	 * Get category via identifier
	 * 
	 * @throws ResourceNotFoundException if {@link Category} not found
	 * @param id identifier of {@link Category}
	 * @return category as {@link CategoryDto}
	 */
	ResponseEntity<CategoryDto> read(Long id);
	
	/**
	 * Update category via identifier
	 * 
	 * @throws ResourceNotFoundException if {@link Category} not found
	 * @param id identifier of {@link Category}
	 * @param data data for {@link Category}
	 * @return category as {@link CategoryDto}
	 */
	ResponseEntity<CategoryDto> update(Long id, CategoryDataDto data);
	
	/**
	 * Delete category via identifier
	 * 
	 * @throws ResourceNotFoundException if {@link Category} not found
	 * @param id identifier of {@link Category}
	 */
	void delete(Long id);
	
}
