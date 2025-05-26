package com.github.lotashinski.client;

import java.util.List;

import com.github.lotashinski.dto.CategoryCollectionItemDto;

import feign.RequestLine;

public interface CategoriesClient {

	@RequestLine("GET /categories")
	List<? extends CategoryCollectionItemDto> findByCriteria();

}
