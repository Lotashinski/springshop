package com.github.lotashinski.ui.service;

import java.util.Set;

import com.github.lotashinski.api.dto.ProductCollectionItemDto;

public interface BucketService {
	
	Set<? extends ProductCollectionItemDto> getProducts();
	
	int getCount(ProductCollectionItemDto product);
	
	void putProduct(ProductCollectionItemDto product, int count);
	
	void purgeProduct(ProductCollectionItemDto product);
	
}
