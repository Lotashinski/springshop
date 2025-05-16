package com.github.lotashinski.service;

import java.util.Collection;

import com.github.lotashinski.dto.CategoryCollectionItemDto;
import com.github.lotashinski.dto.CategoryDataDto;
import com.github.lotashinski.dto.CategoryCriteriaDto;
import com.github.lotashinski.dto.CategoryDto;

public interface CategoryService {

	Collection<? extends CategoryCollectionItemDto> findByCriteria(CategoryCriteriaDto criteria);
	
	CategoryDto create(CategoryDataDto dto);
	
	CategoryDto read(Long id);
	
	CategoryDto update(Long id, CategoryDataDto category);
	
	void delete(Long id);
	
}
