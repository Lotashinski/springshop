package com.github.lotashinski.ui.service.impl;

import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Service;

import com.github.lotashinski.api.dto.ProductCollectionItemDto;
import com.github.lotashinski.api.dto.ProductCriteriaDto;
import com.github.lotashinski.api.dto.ProductDataDto;
import com.github.lotashinski.api.dto.ProductDto;
import com.github.lotashinski.ui.client.ProductClient;
import com.github.lotashinski.ui.service.ProductService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {

	private final ProductClient productClient;
	
	@Override
	public List<? extends ProductCollectionItemDto> getAll(Collection<? extends Long> categories) {
		log.debug("Get products by categories {}", categories);
		
		ProductCriteriaDto criteria = new ProductCriteriaDto();
		criteria.setCategories(categories);
		
		return productClient.findByCriteria(criteria);
	}

	@Override
	public ProductDto getById(Long id) {
		log.debug("Get product by id {}", id);
		
		return null;
	}

	@Override
	public ProductDto update(Long id, ProductDataDto data) {
		log.debug("Update product by id {} {}", id, data);
		
		return productClient.update(id, data);
	}

	@Override
	public ProductDto create(ProductDataDto data) {
		log.debug("Create product {}", data);
		
		return productClient.create(data);
	}

	@Override
	public void delete(Long id) {
		log.debug("Delete product {}", id);
		
		productClient.delete(id);
	}

}
