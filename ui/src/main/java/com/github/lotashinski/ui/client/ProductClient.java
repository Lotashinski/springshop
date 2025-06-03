package com.github.lotashinski.ui.client;

import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;

import com.github.lotashinski.api.dto.ProductCollectionItemDto;
import com.github.lotashinski.api.dto.ProductCriteriaDto;
import com.github.lotashinski.api.dto.ProductDataDto;
import com.github.lotashinski.api.dto.ProductDto;

import feign.Headers;
import feign.Param;
import feign.QueryMap;
import feign.RequestLine;

public interface ProductClient {

	@RequestLine("GET /products")
	List<? extends ProductCollectionItemDto> findByCriteria(@QueryMap ProductCriteriaDto criteria);
	
	@RequestLine("GET /products/{id}")
	ProductDto getById(@Param("id") Long id);
	
	@RequestLine("PUT /products/{id}")
	@Headers("Content-Type: application/json")
	ProductDto update(@Param("id") Long id, @RequestBody ProductDataDto data);
	
	@RequestLine("POST /products")
	@Headers("Content-Type: application/json")
	ProductDto create(@RequestBody ProductDataDto data);
	
	@RequestLine("DELETE /products/{id}")
	void delete(@Param("id") Long id);
	
}
