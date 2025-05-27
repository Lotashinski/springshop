package com.github.lotashinski.api.service;

import java.util.List;

import com.github.lotashinski.api.dto.ProductCollectionItemDto;
import com.github.lotashinski.api.dto.ProductCriteriaDto;
import com.github.lotashinski.api.dto.ProductDataDto;
import com.github.lotashinski.api.dto.ProductDto;

public interface ProductService {
	
	List<? extends ProductCollectionItemDto> findByCriteria(ProductCriteriaDto criteria);
	
	ProductDto create(ProductDataDto dto);
	
	ProductDto read(Long id);
	
	ProductDto update(Long id, ProductDataDto data);
	
	void delete(Long id);
	
}
