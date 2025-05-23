package com.github.lotashinski.service;

import java.util.List;

import com.github.lotashinski.dto.CategoryCollectionItemDto;
import com.github.lotashinski.dto.CategoryDataDto;
import com.github.lotashinski.dto.CategoryCriteriaDto;
import com.github.lotashinski.dto.CategoryDto;

public interface CategoryService {

	List<? extends CategoryCollectionItemDto> findByCriteria(CategoryCriteriaDto criteria);
	
	CategoryDto create(CategoryDataDto dto);
	
	CategoryDto read(Long id);
	
	CategoryDto update(Long id, CategoryDataDto data);
	
	void delete(Long id);
	
}
