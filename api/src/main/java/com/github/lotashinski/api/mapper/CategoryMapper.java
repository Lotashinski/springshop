package com.github.lotashinski.api.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

import com.github.lotashinski.api.entity.Category;
import com.github.lotashinski.api.dto.CategoryCollectionItemDto;
import com.github.lotashinski.api.dto.CategoryDataDto;
import com.github.lotashinski.api.dto.CategoryDto;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CategoryMapper {
	
	CategoryDto toDto(Category entity);
	
	CategoryCollectionItemDto toItemDto(Category entity);
	
	List<CategoryCollectionItemDto> toItemDtoList(List<? extends Category> entities);
	
	@Mapping(target = "products", ignore = true)
	Category toEntity(CategoryDataDto dto, @MappingTarget Category category);
	
}
