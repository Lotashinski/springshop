package com.github.lotashinski.ui.service.impl;

import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Service;

import com.github.lotashinski.api.dto.ProductCollectionItemDto;
import com.github.lotashinski.api.dto.ProductCriteriaDto;
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
		ProductCriteriaDto criteria = new ProductCriteriaDto();
		criteria.setCategories(categories);
		
		return productClient.findByCriteria(criteria);
	}

}
