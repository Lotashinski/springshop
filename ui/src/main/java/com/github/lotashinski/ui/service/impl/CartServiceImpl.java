package com.github.lotashinski.ui.service.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
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
	
	private Map<Long, Integer> bucket = new HashMap<>();
	
	@Override
	public void putProduct(Long product, int count) {
		bucket.put(product, count);		
	}

	@Override
	public void purgeProduct(Long product) {
		bucket.remove(product);		
	}

	@Override
	public Set<? extends ProductCollectionItemDto> getProducts() {
		if (bucket.isEmpty()) return Set.of();
		
		ProductCriteriaDto criteria = new ProductCriteriaDto();
		criteria.setIds(getProductIds());
		
		return new HashSet<>(productClient.findByCriteria(criteria));
	}

	@Override
	public int getCount(Long product) {
		return bucket.getOrDefault(product, 0);
	}

	@Override
	public void putProduct(Long productId) {
		if (bucket.containsKey(productId)) return;
		
		putProduct(productId, 1);
	}

	@Override
	public Set<? extends Long> getProductIds() {
		return bucket.keySet();
	}

}
