package com.github.lotashinski.ui.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import com.github.lotashinski.api.dto.ProductCollectionItemDto;
import com.github.lotashinski.ui.service.BucketService;

@Service
@SessionScope
public class BucketServiceImpl implements BucketService {
	
	private Map<ProductCollectionItemDto, Integer> bucket = new HashMap<>();
	
	@Override
	public void putProduct(ProductCollectionItemDto product, int count) {
		bucket.put(product, count);		
	}

	@Override
	public void purgeProduct(ProductCollectionItemDto product) {
		bucket.remove(product);		
	}

	@Override
	public Set<? extends ProductCollectionItemDto> getProducts() {
		return bucket.entrySet()
				.stream()
				.map(e -> e.getKey())
				.sorted((l, r) -> l.getTitle().compareTo(r.getTitle()))
				.collect(Collectors.toSet());
	}

	@Override
	public int getCount(ProductCollectionItemDto product) {
		return bucket.getOrDefault(product, 0);
	}

}
