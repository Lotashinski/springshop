package com.github.lotashinski.ui.client;

import java.util.List;

import com.github.lotashinski.api.dto.ProductCollectionItemDto;

import feign.RequestLine;

public interface ProductClient {

	@RequestLine("GET /products")
	List<? extends ProductCollectionItemDto> findByCriteria();
	
}
