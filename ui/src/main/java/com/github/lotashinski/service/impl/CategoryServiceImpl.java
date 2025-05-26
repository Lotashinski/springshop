package com.github.lotashinski.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.github.lotashinski.client.CategoriesClient;
import com.github.lotashinski.dto.CategoryCollectionItemDto;
import com.github.lotashinski.service.CategoriesService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoriesService {

	private final CategoriesClient categories;
	
	@Override
	public List<? extends CategoryCollectionItemDto> getAll() {
		return categories.findByCriteria();
	}
	
}
