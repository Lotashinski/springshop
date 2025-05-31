package com.github.lotashinski.ui.client;

import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;

import com.github.lotashinski.api.dto.CategoryCollectionItemDto;
import com.github.lotashinski.api.dto.CategoryCriteriaDto;
import com.github.lotashinski.api.dto.CategoryDataDto;
import com.github.lotashinski.api.dto.CategoryDto;

import feign.Headers;
import feign.Param;
import feign.QueryMap;
import feign.RequestLine;

public interface CategoriesClient {

	@RequestLine("GET /categories")
	List<? extends CategoryCollectionItemDto> findByCriteria(@QueryMap CategoryCriteriaDto criteria);

	@RequestLine("GET /categories/{id}")
	CategoryDto getById(@Param("id") Long id);
	
	@RequestLine("PUT /categories/{id}")
	@Headers("Content-Type: application/json")
	CategoryDto update(@Param("id") Long id, @RequestBody CategoryDataDto data);
	
	@RequestLine("POST /categories")
	@Headers("Content-Type: application/json")
	CategoryDto create(@RequestBody CategoryDataDto data);
	
	@RequestLine("DELETE /categories/{id}")
	void delete(@Param("id") Long id);
	
}
