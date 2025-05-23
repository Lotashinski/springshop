package com.github.lotashinski.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

import com.github.lotashinski.dto.CategoryCollectionItemDto;
import com.github.lotashinski.dto.CategoryDataDto;
import com.github.lotashinski.dto.CategoryDto;
import com.github.lotashinski.entity.Category;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CategoryMapper {
	
	CategoryDto toDto(Category entity);
	
	CategoryCollectionItemDto toItemDto(Category entity);
	
	List<CategoryCollectionItemDto> toItemDtoList(List<? extends Category> entities);
	
	Category toEntity(CategoryDataDto dto, @MappingTarget Category category);
	
}
