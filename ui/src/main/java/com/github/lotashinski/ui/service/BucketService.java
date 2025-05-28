package com.github.lotashinski.ui.service;

import java.util.Set;

import com.github.lotashinski.api.dto.ProductCollectionItemDto;

public interface BucketService {
	
	Set<? extends ProductCollectionItemDto> getProducts();
	
	int getCount(Long productId);
	
	void putProduct(Long productId);
	
	void putProduct(Long productId, int count);
	
	void purgeProduct(Long productId);
	
}
