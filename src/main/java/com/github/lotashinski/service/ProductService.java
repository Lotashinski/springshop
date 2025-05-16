package com.github.lotashinski.service;

import java.util.Collection;

import com.github.lotashinski.dto.ProductCollectionItemDto;
import com.github.lotashinski.dto.ProductCriteriaDto;
import com.github.lotashinski.dto.ProductDataDto;
import com.github.lotashinski.dto.ProductDto;

public interface ProductService {
	
	Collection<? extends ProductCollectionItemDto> findByCriteria(ProductCriteriaDto criteria);
	
	ProductDto create(ProductDataDto dto);
	
	ProductDto read(Long id);
	
	ProductDto update(Long id, ProductDataDto category);
	
	void delete(Long id);
	
}
