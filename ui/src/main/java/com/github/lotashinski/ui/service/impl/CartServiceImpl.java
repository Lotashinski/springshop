package com.github.lotashinski.ui.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import com.github.lotashinski.api.dto.ProductCollectionItemDto;
import com.github.lotashinski.api.dto.ProductCriteriaDto;
import com.github.lotashinski.ui.client.ProductClient;
import com.github.lotashinski.ui.service.CartService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@SessionScope
@Slf4j
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

	private final ProductClient productClient;
	
	private Set<Long> bucket = new HashSet<>();
	
	@Override
	public void purgeProduct(Long product) {
		log.debug("Add product {}", product);
		
		bucket.remove(product);		
	}

	@Override
	public List<? extends ProductCollectionItemDto> getProducts() {
		log.debug("Get product");
		
		if (bucket.isEmpty()) return List.of();
		
		ProductCriteriaDto criteria = new ProductCriteriaDto();
		criteria.setIds(getProductIds());
		
		return productClient.findByCriteria(criteria);
	}

	@Override
	public void putProduct(Long productId) {
		log.debug("Put product {}", productId);
		
		bucket.add(productId);
	}

	@Override
	public Set<? extends Long> getProductIds() {
		log.debug("Get products");
		
		return bucket;
	}

	@Override
	public void clear() {
		log.debug("Clear cart");
		
		bucket.clear();
	}

}
