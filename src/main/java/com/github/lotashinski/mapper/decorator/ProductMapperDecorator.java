package com.github.lotashinski.mapper.decorator;

import java.util.Collection;
import java.util.HashSet;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.github.lotashinski.dto.ProductDataDto;
import com.github.lotashinski.entity.Category;
import com.github.lotashinski.entity.Product;
import com.github.lotashinski.exceptions.ResourceNotFoundException;
import com.github.lotashinski.mapper.ProductMapper;
import com.github.lotashinski.repository.CategoryRepository;

import lombok.Setter;

public abstract class ProductMapperDecorator implements ProductMapper {

	@Autowired
	@Setter
	private CategoryRepository categoryRepository;
	
	@Override
	public Product toEntity(ProductDataDto dto, Product entity) {
		Collection<Long> categoriesIds = new HashSet<>(dto.getCategories());
		Collection<Category> categories = categoryRepository.findAllById(categoriesIds);
		
		if (categoriesIds.size() != categories.size()) {
			Collection<Long> containsIds = categories.stream()
				.map(Category::getId)
				.collect(Collectors.toSet());
			categoriesIds.removeAll(containsIds);
			
			throw new ResourceNotFoundException(String.format("Categories not found: %s", categoriesIds.toString()));
		}
		
		entity.getCategories()
		 	.addAll(categories);
		
		return entity;
	}
	
}
