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

@Service
@SessionScope
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

	private final ProductClient productClient;
	
	private Set<Long> bucket = new HashSet<>();
	
	@Override
	public void purgeProduct(Long product) {
		bucket.remove(product);		
	}

	@Override
	public List<? extends ProductCollectionItemDto> getProducts() {
		if (bucket.isEmpty()) return List.of();
		
		ProductCriteriaDto criteria = new ProductCriteriaDto();
		criteria.setIds(getProductIds());
		
		return productClient.findByCriteria(criteria);
	}

	@Override
	public void putProduct(Long productId) {
		bucket.add(productId);
	}

	@Override
	public Set<? extends Long> getProductIds() {
		return bucket;
	}

}
