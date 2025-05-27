package com.github.lotashinski.api.service;

import java.util.List;

import com.github.lotashinski.api.dto.CategoryCollectionItemDto;
import com.github.lotashinski.api.dto.CategoryDataDto;
import com.github.lotashinski.api.dto.CategoryCriteriaDto;
import com.github.lotashinski.api.dto.CategoryDto;

public interface CategoryService {

	List<? extends CategoryCollectionItemDto> findByCriteria(CategoryCriteriaDto criteria);
	
	CategoryDto create(CategoryDataDto dto);
	
	CategoryDto read(Long id);
	
	CategoryDto update(Long id, CategoryDataDto data);
	
	void delete(Long id);
	
}
