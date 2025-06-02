package com.github.lotashinski.api.service;

import java.util.List;

import com.github.lotashinski.api.dto.CategoryCollectionItemDto;
import com.github.lotashinski.api.dto.CategoryCriteriaDto;
import com.github.lotashinski.api.dto.CategoryDataDto;
import com.github.lotashinski.api.dto.CategoryDto;
import com.github.lotashinski.api.entity.Category;

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
	 * @param criteria
	 * @return
	 */
	List<? extends CategoryCollectionItemDto> findByCriteria(CategoryCriteriaDto criteria);
	
	CategoryDto create(CategoryDataDto dto);
	
	CategoryDto read(Long id);
	
	CategoryDto update(Long id, CategoryDataDto data);
	
	void delete(Long id);
	
}
