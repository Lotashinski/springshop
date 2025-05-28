package com.github.lotashinski.ui.service;

import java.util.Collection;
import java.util.List;

import com.github.lotashinski.api.dto.ProductCollectionItemDto;

public interface ProductService {

	List<? extends ProductCollectionItemDto> getAll(Collection<? extends Long> categories);
	
}
