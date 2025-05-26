package com.github.lotashinski.service;

import java.util.List;

import com.github.lotashinski.dto.CategoryCollectionItemDto;

public interface CategoriesService {
	
	List<? extends CategoryCollectionItemDto> getAll();	
}
