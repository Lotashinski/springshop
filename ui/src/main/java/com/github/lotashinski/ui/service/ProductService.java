package com.github.lotashinski.ui.service;

import java.util.Collection;
import java.util.List;

import com.github.lotashinski.api.dto.ProductCollectionItemDto;
import com.github.lotashinski.api.dto.ProductDataDto;
import com.github.lotashinski.api.dto.ProductDto;

public interface ProductService {

	List<? extends ProductCollectionItemDto> getAll(Collection<? extends Long> categories);
	
	ProductDto getById(Long id);
	
	ProductDto update(Long id, ProductDataDto data);
	
	ProductDto create(ProductDataDto data);
	
	void delete(Long id);
	
}
