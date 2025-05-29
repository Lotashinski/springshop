package com.github.lotashinski.ui.service;

import java.util.List;
import java.util.Set;

import com.github.lotashinski.api.dto.ProductCollectionItemDto;

public interface CartService {
	
	Set<? extends Long> getProductIds();
	
	List<? extends ProductCollectionItemDto> getProducts();
	
	void putProduct(Long productId);
	
	void purgeProduct(Long productId);
	
}
