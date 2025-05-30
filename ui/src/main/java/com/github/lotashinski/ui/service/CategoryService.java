package com.github.lotashinski.ui.service;

import java.util.List;

import com.github.lotashinski.api.dto.CategoryCollectionItemDto;

public interface CategoryService {
	
	List<? extends CategoryCollectionItemDto> getAll();	
}
