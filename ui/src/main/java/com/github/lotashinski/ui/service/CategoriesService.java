package com.github.lotashinski.ui.service;

import java.util.List;

import com.github.lotashinski.api.dto.CategoryCollectionItemDto;

public interface CategoriesService {
	
	List<? extends CategoryCollectionItemDto> getAll();	
}
