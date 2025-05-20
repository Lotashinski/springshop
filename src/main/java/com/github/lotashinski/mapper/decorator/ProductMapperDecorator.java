package com.github.lotashinski.mapper.decorator;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

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
	
	@Autowired
	@Setter
	@Qualifier("delegate")
	private ProductMapper delegate;
	
	@Override
	public Product toEntity(ProductDataDto dto, Product entity) {
		entity = delegate.toEntity(dto, entity);
		Collection<Long> categoriesIds =  new HashSet<>(dto.getCategories() == null ? Set.of() : dto.getCategories());
		Collection<Category> categories = categoryRepository.findAllById(categoriesIds);
		
		if (categoriesIds.size() != categories.size()) {
			Collection<Long> containsIds = categories.stream()
				.map(Category::getId)
				.collect(Collectors.toSet());
			categoriesIds.removeAll(containsIds);
			
			throw new ResourceNotFoundException(String.format("Categories not found: %s", categoriesIds.toString()));
		}
		
		entity.getCategories()
			.clear();
		entity.getCategories()
		 	.addAll(categories);
		
		return entity;
	}
	
}
