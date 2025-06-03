package com.github.lotashinski.ui.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.github.lotashinski.api.dto.CategoryCollectionItemDto;
import com.github.lotashinski.api.dto.CategoryCriteriaDto;
import com.github.lotashinski.api.dto.CategoryDataDto;
import com.github.lotashinski.api.dto.CategoryDto;
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

	@Override
	public CategoryDto getById(Long id) {
		log.debug("Get category by id {}", id);
		
		return categories.getById(id);
	}

	@Override
	public CategoryDto update(Long id, CategoryDataDto data) {
		log.debug("Update category by id {} {}", id, data);
		
		return categories.update(id, data);
	}

	@Override
	public CategoryDto create(CategoryDataDto data) {
		log.debug("Create category {}", data);
		
		return categories.create(data);
	}

	@Override
	public void delete(Long id) {
		log.debug("Delete category {}", id);
		categories.delete(id);		
	}
	
}
