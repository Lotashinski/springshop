package com.github.lotashinski.service;

import java.util.List;

import com.github.lotashinski.dto.ProductCollectionItemDto;
import com.github.lotashinski.dto.ProductCriteriaDto;
import com.github.lotashinski.dto.ProductDataDto;
import com.github.lotashinski.dto.ProductDto;

public interface ProductService {
	
	List<? extends ProductCollectionItemDto> findByCriteria(ProductCriteriaDto criteria);
	
	ProductDto create(ProductDataDto dto);
	
	ProductDto read(Long id);
	
	ProductDto update(Long id, ProductDataDto data);
	
	void delete(Long id);
	
}
