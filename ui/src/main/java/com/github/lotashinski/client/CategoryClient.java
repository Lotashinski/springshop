package com.github.lotashinski.client;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;

import com.github.lotashinski.dto.CategoryCollectionItemDto;


public interface CategoryClient {

	@GetMapping()
	List<? extends CategoryCollectionItemDto> findByCriteria();
	
}
