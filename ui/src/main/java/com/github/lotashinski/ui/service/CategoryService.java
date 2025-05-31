package com.github.lotashinski.ui.service;

import java.util.List;

import com.github.lotashinski.api.dto.CategoryCollectionItemDto;
import com.github.lotashinski.api.dto.CategoryDataDto;
import com.github.lotashinski.api.dto.CategoryDto;

public interface CategoryService {
	
	List<? extends CategoryCollectionItemDto> getAll();
	
	CategoryDto getById(Long id);
	
	CategoryDto update(Long id, CategoryDataDto data);
	
	CategoryDto create(CategoryDataDto data);
	
	void delete(Long id);
	
}
