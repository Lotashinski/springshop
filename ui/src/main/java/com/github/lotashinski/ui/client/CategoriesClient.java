package com.github.lotashinski.ui.client;

import java.util.List;

import com.github.lotashinski.api.dto.CategoryCollectionItemDto;
import com.github.lotashinski.api.dto.CategoryCriteriaDto;

import feign.QueryMap;
import feign.RequestLine;

public interface CategoriesClient {

	@RequestLine("GET /categories")
	List<? extends CategoryCollectionItemDto> findByCriteria(@QueryMap CategoryCriteriaDto criteria);

}
