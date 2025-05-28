package com.github.lotashinski.ui.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import com.github.lotashinski.api.dto.ProductCollectionItemDto;
import com.github.lotashinski.ui.service.BucketService;

@Service
@SessionScope
public class BucketServiceImpl implements BucketService {
	
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
		return null;
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

}
