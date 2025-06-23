package com.github.lotashinski.api.mapper;

import java.util.List;

import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

import com.github.lotashinski.api.entity.Product;
import com.github.lotashinski.api.mapper.decorator.ProductMapperDecorator;
import com.github.lotashinski.api.dto.ProductCollectionItemDto;
import com.github.lotashinski.api.dto.ProductDataDto;
import com.github.lotashinski.api.dto.ProductDto;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
@DecoratedWith(ProductMapperDecorator.class)
public interface ProductMapper {

	ProductDto toDto(Product entity);
	
	ProductCollectionItemDto toItemDto(Product entity);
	
	List<ProductCollectionItemDto> toItemDtoList(List<? extends Product> entities);
	
	@Mapping(target = "categories", ignore = true)
	@Mapping(target = "isDeleted", ignore = true)
	Product toEntity(ProductDataDto dto, @MappingTarget Product entity);
	
}
