package com.github.lotashinski.ui.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.github.lotashinski.api.dto.CategoryCollectionItemDto;
import com.github.lotashinski.api.dto.CategoryCriteriaDto;
import com.github.lotashinski.ui.client.CategoriesClient;
import com.github.lotashinski.ui.service.CategoryService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

	private final CategoriesClient categories;
	
	@Override
	public List<? extends CategoryCollectionItemDto> getAll() {
		log.debug("Get all categories");
		
		return categories.findByCriteria(new CategoryCriteriaDto());
	}
	
}
